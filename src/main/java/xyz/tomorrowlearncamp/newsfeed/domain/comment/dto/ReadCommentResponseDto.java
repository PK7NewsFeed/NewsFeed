package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

import java.sql.Timestamp;

@Getter
public class ReadCommentResponseDto {

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
    public ReadCommentResponseDto(Long id, Long userId, Long feedId, Long parentCommentId, String username, String feedname, String content, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        this.username = username;
        this.feedname = feedname;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReadCommentResponseDto toDto(Comment comment) {
        return new ReadCommentResponseDto(
                comment.getId(),
                comment.getUser().getId(),
                comment.getNewsFeed().getId(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                comment.getUser().getUsername(),
                comment.getNewsFeed().getTitle(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
