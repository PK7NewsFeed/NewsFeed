package xyz.tomorrowlearncamp.newsfeed.auth.service;

import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;

public interface AuthService {

    SignUpUserResponseDto signUp(String email, String password, String username);

    LoginUserResponseDto login(String email, String password);
}
