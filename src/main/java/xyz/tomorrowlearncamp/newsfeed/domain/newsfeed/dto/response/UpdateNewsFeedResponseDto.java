package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateNewsFeedResponseDto {

    private final Long id;
    private final String title;

    @Builder()
    public UpdateNewsFeedResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
