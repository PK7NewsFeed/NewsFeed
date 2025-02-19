package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto;

import lombok.Getter;

@Getter
public class NewsFeedResponseDto {

    private final Long id;
    private final String title;
    private final String content;

    public NewsFeedResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
