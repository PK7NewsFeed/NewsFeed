package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LoginUserResponse {

    private Long id;

    private String username;



    @Builder
    public LoginUserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
