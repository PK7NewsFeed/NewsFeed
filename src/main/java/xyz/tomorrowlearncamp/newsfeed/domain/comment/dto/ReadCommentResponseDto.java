package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class ReadCommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long feedId;

    private final String username;

    private final String feedname;

    private final String content;

    private final Long parentCommentId;

    private final int likeCount;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    @Builder
    public ReadCommentResponseDto(Long id, Long userId, Long feedId, Long parentCommentId, String username, String feedname, String content, int likeCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        this.username = username;
        this.feedname = feedname;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReadCommentResponseDto toDto(Comment comment, int likeCount) {
        return ReadCommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .updatedAt(comment.getUpdatedAt())
                .createdAt(comment.getCreatedAt())
                .feedId(comment.getNewsFeed().getId())
                .feedname(comment.getNewsFeed().getTitle())
                .parentCommentId(comment.getParentComment().getId())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .likeCount(likeCount)
                .build();
    }


}
