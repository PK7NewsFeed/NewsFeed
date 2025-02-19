package xyz.tomorrowlearncamp.newsfeed.global.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.tomorrowlearncamp.newsfeed.auth.service.AuthService;
import xyz.tomorrowlearncamp.newsfeed.global.filter.JwtLoginFilter;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtUtil jwtUtil;

    private final AuthService authService;

    @Bean
    public FilterRegistrationBean<Filter> loginJwtFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new JwtLoginFilter(jwtUtil, authService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
