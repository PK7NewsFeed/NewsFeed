package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateNewsFeedRequestDto {

    private String title;
    private String content;
}
