package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.global.etc.ErrorMessage;

@Getter
@RequiredArgsConstructor
public class CreateNewsFeedRequestDto {

    @NotNull(message = ErrorMessage.EMPTY_CONTENT)
    @Size(min = 1, max = 30, message = "길이가 너무 짧거나 깁니다.")
    private final String title;

    @NotNull(message = ErrorMessage.EMPTY_CONTENT)
    @Size(min = 1, max = 50, message = "길이가 너무 짧거나 깁니다.")
    private final String content;
}
