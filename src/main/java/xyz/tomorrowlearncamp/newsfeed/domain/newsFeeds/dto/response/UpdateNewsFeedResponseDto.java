package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.response;

import lombok.Getter;

@Getter
public class UpdateNewsFeedResponseDto {

    private final Long id;
    private final String title;

    public UpdateNewsFeedResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
