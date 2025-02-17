package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.dto;

import lombok.Getter;

@Getter
public class CommentLikeRequestDto {

    private final Long userId;

    private final Long commentId;

    public CommentLikeRequestDto(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
