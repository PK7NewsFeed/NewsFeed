package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateCommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long feedId;

    private final String username;

    private final String feedname;

    private final String content;

    private final Long parentCommentId;

    private final Timestamp createdAt;

    private final Timestamp updatedAt;
    @Builder
    public UpdateCommentResponseDto(Long id, Long userId, Long feedId, String username, String feedname, String content, Long parentCommentId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.feedId = feedId;
        this.username = username;
        this.feedname = feedname;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
