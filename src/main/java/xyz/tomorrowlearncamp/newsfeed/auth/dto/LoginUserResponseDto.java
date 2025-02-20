package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginUserResponseDto {

    private final Long id;

    private final String username;

    @Builder
    public LoginUserResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
