package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequestDto {

    @NotBlank(message = "댓글 내용을 입력해주세요")
    private final String content;

    public UpdateCommentRequestDto(String content) {
        this.content = content;
    }
}
