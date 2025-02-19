package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NewsFeedRequestDto {

    @NotNull(message = "값이 비어있습니다.")
    @Size(min = 1, max = 30, message = "길이가 너무 짧거나 깁니다.")
    private String title;

    @NotNull(message = "값이 비어있습니다.")
    @Size(min = 1, max = 50, message = "길이가 너무 짧거나 깁니다.")
    private String content;
}
