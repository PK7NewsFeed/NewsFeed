package xyz.tomorrowlearncamp.newsfeed.global.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import xyz.tomorrowlearncamp.newsfeed.auth.service.AuthService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.exception.ErrorCode;
import xyz.tomorrowlearncamp.newsfeed.global.exception.ErrorMessage;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtLoginFilter implements Filter {
    private final static String[] WHILE_LIST = {
            "/auth/signup",
            "/auth/login",
            "/auth/jwt/login",
    };

    private final JwtUtil jwtUtil;

    private final AuthService authService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUri = request.getRequestURI();

        if( !isWhileList(requestUri) ) {
            String jwt = request.getHeader(JwtProperties.HEADER_STRING);
            String token = jwt.replace(JwtProperties.TOKEN_PREFIX, "");

            Date ExpiresDate = JWT.decode(token).getExpiresAt();


            // JWT 가 비어있거나, 만료 날짜가 지난 경우
            if( jwt.isEmpty() || ExpiresDate.before(new Date()) ) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorMessage(ErrorCode.NOT_LOGIN, HttpStatus.UNAUTHORIZED)));
                return;
            }

            // jwt 검증 -> jwt 안에 있는  ID값이 존재 하는가
            Long userId = jwtUtil.extractUserId(jwt);
            if (!authService.existsById(userId)) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorMessage(ErrorCode.NOT_FOUND_USER, HttpStatus.UNAUTHORIZED)));
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    // WHITE_LIST 에 속해 있는가
    private boolean isWhileList(String uri) {
        return PatternMatchUtils.simpleMatch(WHILE_LIST, uri);
    }

}
