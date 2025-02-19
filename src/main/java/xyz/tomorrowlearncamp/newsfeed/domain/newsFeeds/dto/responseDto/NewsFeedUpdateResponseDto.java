package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto;

import lombok.Getter;

@Getter
public class NewsFeedUpdateResponseDto {

    private final Long id;
    private final String title;

    public NewsFeedUpdateResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
