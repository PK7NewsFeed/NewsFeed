package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

@Getter
public class CreateCommentRequestDto {

    @NotBlank(message = "댓글을 입력하세요")
    private final String content;

    @NotNull(message = "사용자 필수")
    private final Long userId;

    @NotNull(message = "게시글 필수")
    private final Long newsFeedId;

    private final Integer depth;

    @Nullable
    private final Long parentCommentId;

    public CreateCommentRequestDto(String content, Long userId, Long newsFeedId, Integer depth, @Nullable Long parentCommentId) {
        this.content = content;
        this.userId = userId;
        this.newsFeedId = newsFeedId;
        this.depth = (depth != null) ? depth : 0;
        this.parentCommentId = parentCommentId;
    }
}
