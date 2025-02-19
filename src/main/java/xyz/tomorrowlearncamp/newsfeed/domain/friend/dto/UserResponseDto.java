package xyz.tomorrowlearncamp.newsfeed.domain.friend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String username;
    private final String email;

    @Builder
    public UserResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
