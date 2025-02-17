package xyz.tomorrowlearncamp.newsfeed.auth.service;

import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponse;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;

import java.sql.Timestamp;

public interface AuthService {

    SignUpUserResponseDto signUp(String email, String password, String username, Gender gender, Timestamp birthDate);

    LoginUserResponse login(String email, String password);
}
