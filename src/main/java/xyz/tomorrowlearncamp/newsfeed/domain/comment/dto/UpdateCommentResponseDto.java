package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentResponseDto {

    private final String content;

    public UpdateCommentResponseDto(String content) {
        this.content = content;
    }
}
