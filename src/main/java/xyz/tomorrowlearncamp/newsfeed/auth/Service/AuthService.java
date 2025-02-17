package xyz.tomorrowlearncamp.newsfeed.auth.Service;

import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponse;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserRequestDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;

public interface AuthService {

    SignUpUserResponseDto signUp(String email, String password, String username);

    LoginUserResponse login(String email, String password);
}
