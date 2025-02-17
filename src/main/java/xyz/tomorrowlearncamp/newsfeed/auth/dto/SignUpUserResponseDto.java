package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SignUpUserResponseDto {

    private Long id;

    private String email;

    private String username;

    private LocalDateTime createdAt;

    @Builder
    public SignUpUserResponseDto(Long id, String email, String username, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.createdAt = createdAt;
    }
}
