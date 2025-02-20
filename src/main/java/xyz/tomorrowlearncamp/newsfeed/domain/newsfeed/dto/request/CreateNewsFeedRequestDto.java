package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

@Getter
@RequiredArgsConstructor
public class CreateNewsFeedRequestDto {

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 1, max = 30, message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    private final String title;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 1, max = 50, message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    private final String content;
}
