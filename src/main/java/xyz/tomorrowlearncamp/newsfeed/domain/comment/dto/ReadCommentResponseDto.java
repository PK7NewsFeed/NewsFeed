package xyz.tomorrowlearncamp.newsfeed.domain.comment.dto;

import lombok.Getter;

@Getter
public class ReadCommentResponseDto {

    private final User user;

    private final NewsFeed newsFeed;
}
