package xyz.tomorrowlearncamp.newsfeed.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.exception.ErrorCode;
import xyz.tomorrowlearncamp.newsfeed.global.exception.ErrorMessage;

import java.io.IOException;


public class LoginFilter implements Filter {

    private final static String[] WHILE_LIST = {
            "/auth/signup",
            "/auth/login",
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUri = request.getRequestURI();

        if( !isWhileList(requestUri) ) {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorMessage(ErrorCode.NOT_LOGIN, HttpStatus.UNAUTHORIZED)));
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
