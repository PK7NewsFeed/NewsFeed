package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateCommentRequestDto {
    @NotBlank(message = "댓글 내용을 입력해주세요")
    @Size(min = 2, max = 1000)
    private final String content;

    public UpdateCommentRequestDto(String content) {
        this.content = content;
    }
}
