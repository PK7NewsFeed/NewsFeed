package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    @NotBlank(message = "댓글을 입력하세요")
    @Size(min = 2, max = 1000)
    private final String content;

    @NotNull(message = "게시글 필수")
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
