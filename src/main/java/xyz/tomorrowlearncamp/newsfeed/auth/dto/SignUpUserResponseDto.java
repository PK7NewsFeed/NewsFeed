package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class SignUpUserResponseDto {

    private final Long id;

    private final String email;

    private final String username;

    private final LocalDateTime createdAt;

    @Builder
    public SignUpUserResponseDto(Long id, String email, String username, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
    }
}
