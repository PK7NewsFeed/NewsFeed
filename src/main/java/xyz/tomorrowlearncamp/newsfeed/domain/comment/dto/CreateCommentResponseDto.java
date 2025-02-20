package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CreateCommentResponseDto {

    private final Long id;

    private final Long userId;

    private final Long feedId;

    private final String username;

    private final String feedname;

    private final String content;

    private final Long parentCommentId;

    private final int likeCount = 0;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    @Builder
    public CreateCommentResponseDto(
            Long id,
            Long userId,
            Long feedId,
            String content,
            Long parentCommentId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String username,
            String feedname
    ) {
        this.id = id;
        this.userId = userId;
        this.feedId = feedId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
        this.feedname = feedname;
    }
}
