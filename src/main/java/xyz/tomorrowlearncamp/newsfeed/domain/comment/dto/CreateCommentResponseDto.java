package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

import java.sql.Timestamp;

@Getter
public class CreateCommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long feedId;

    private final String username;

    private final String feedname;

    private final String content;

    private final Long parentCommentId;

    private final Timestamp createdAt;

    private final Timestamp updatedAt;

    public CreateCommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.feedId = comment.getNewsFeed().getId();
        this.content = comment.getContent();
        this.parentCommentId = (comment.getParentComment() != null) ? comment.getParentComment().getId() : null;
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.username = comment.getUser().getUsername();
        this.feedname = comment.getNewsFeed().getNewsFeedname();
    }
}
