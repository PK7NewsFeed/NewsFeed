package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.dto;

import lombok.Getter;

@Getter
public class CommentLikeRequestDto {

    private final Long commentId;

    public CommentLikeRequestDto(Long commentId) {
        this.commentId = commentId;
    }
}
