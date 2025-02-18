package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

@Getter
public class ReadCommentResponseDto {

    private final Long id;

    private final Long parentCommentId;

    private final String username;

    private final String feedname;


    public ReadCommentResponseDto(Long id, Long parentCommentId, String username, String feedname) {
        this.id = id;
        this.parentCommentId = parentCommentId;
        this.username = username;
        this.feedname = feedname;
    }

    public static ReadCommentResponseDto toDto(Comment comment) {
        return new ReadCommentResponseDto(
                comment.getId(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                comment.getUser().getUsername(),
                comment.getNewsFeed().getFeedname()
        );
    }
}
