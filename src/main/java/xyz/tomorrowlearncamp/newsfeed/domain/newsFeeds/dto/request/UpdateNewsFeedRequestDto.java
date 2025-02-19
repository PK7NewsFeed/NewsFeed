package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateNewsFeedRequestDto {

    private String title;
    private String content;
}
