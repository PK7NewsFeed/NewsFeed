package xyz.tomorrowlearncamp.newsfeed.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PatternMatchUtils;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.exception.LoginUserException;

import java.io.IOException;

public class LoginFilter implements Filter {

    private final static String[] WHILE_LIST = {"/auth/signup", "/auth/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestUri = request.getRequestURI();

        if( !isWhileList(requestUri) ) {
            HttpSession session = request.getSession(false);

            if( session == null || session.getAttribute(Const.LOGIN_USER) == null ) {
                throw new LoginUserException();
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    // WHITE_LIST 에 속해 있는가
    private boolean isWhileList(String uri) {
        return PatternMatchUtils.simpleMatch(WHILE_LIST, uri);
    }
}
