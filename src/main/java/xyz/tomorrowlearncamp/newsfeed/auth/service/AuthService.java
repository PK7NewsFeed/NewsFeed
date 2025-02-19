package xyz.tomorrowlearncamp.newsfeed.auth.service;

import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.enums.Gender;
import java.time.LocalDate;

public interface AuthService {

    SignUpUserResponseDto signUp(String email, String password, String username, Gender gender, LocalDate birthDate);

    LoginUserResponseDto login(String email, String password);

    String jwtLogin(String email, String password);

    boolean existsByEmail(String email);

    boolean existsById(Long userId);
}
