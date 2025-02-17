package xyz.tomorrowlearncamp.newsfeed.auth.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogoutUserResponse {

    private final String message;
}
