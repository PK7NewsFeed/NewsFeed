package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

@Getter
public class CreateNewsFeedRequestDto {

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 1, max = 30, message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    private String title;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 1, max = 50, message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    private String content;
}
