package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateNewsFeedRequestDto {

    private final String title;
    private final String content;
}
