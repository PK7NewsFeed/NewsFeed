package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

@Getter
public class CreateCommentResponseDto {

    private final Long commentId;

    private final Long userId;

    private final Long feedId;

    private final String content;

    private final Long parentCommentId;

    public CreateCommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.userId = comment.getUser().getId();
        this.feedId = comment.getNewsFeed().getId();
        this.content = comment.getContent();
        this.parentCommentId = (comment.getParentComment() != null) ? comment.getParentComment().getId() : null;
    }
}
