package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

@Getter
public class UpdateCommentRequestDto {
    @NotBlank(message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    @Size(min = 2, max = 1000)
    private final String content;

    public UpdateCommentRequestDto(String content) {
        this.content = content;
    }
}
