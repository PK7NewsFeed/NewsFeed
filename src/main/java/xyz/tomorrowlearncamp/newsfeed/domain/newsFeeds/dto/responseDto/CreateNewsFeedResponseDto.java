package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class CreateNewsFeedResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long uesrId;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
}
