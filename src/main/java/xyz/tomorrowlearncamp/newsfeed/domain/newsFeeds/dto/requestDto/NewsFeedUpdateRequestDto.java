package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto;

import lombok.Getter;

@Getter
public class NewsFeedUpdateRequestDto {

    private String title;

    public NewsFeedUpdateRequestDto(String title) {
        this.title = title;
    }
}
