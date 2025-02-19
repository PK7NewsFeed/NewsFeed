package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateNewsFeedRequestDto {

    private String title;
    private String content;
}
