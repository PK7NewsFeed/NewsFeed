package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateCommentRequestDto {

    private final Long id;

    private final Long userId;

    private final Long feedId;

    @NotBlank(message = "댓글 내용을 입력해주세요")
    @Size(min = 2, max = 1000)
    private final String content;

    private final Long parentCommentId;

    private final Integer depth;

    public UpdateCommentRequestDto(Long id, Long userId, Long feedId, String content, Long parentCommentId, Integer depth) {
        this.id = id;
        this.userId = userId;
        this.feedId = feedId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.depth = depth;
    }
}
