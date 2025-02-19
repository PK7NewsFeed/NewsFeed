package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

@Getter
public class CreateCommentRequestDto {

    @NotBlank(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 2, max = 1000, message = DtoErrorMessage.INVALID_CONTENT_LENGTH)
    private final String content;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    private final Long newsFeedId;

    private final Integer depth;

    @Nullable
    private final Long parentCommentId;

    public CreateCommentRequestDto(String content, Long newsFeedId, Integer depth, @Nullable Long parentCommentId) {
        this.content = content;
        this.newsFeedId = newsFeedId;
        this.depth = (depth != null) ? depth : 0;
        this.parentCommentId = parentCommentId;
    }
}
