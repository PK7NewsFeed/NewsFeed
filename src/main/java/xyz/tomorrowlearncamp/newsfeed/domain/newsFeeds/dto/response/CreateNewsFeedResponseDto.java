package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateNewsFeedResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long uesrId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
