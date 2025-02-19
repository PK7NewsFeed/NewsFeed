package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

@Getter
public class UpdateCommentRequestDto {
    @NotBlank(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 2, max = 1000, message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    private final String content;

    public UpdateCommentRequestDto(String content) {
        this.content = content;
    }
}
