package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class ReadNewsFeedResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long uesrId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final int likeCount;

    @Builder
    public ReadNewsFeedResponseDto(
            Long id,
            String title,
            String content,
            Long userId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            int likeCount
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.uesrId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likeCount = likeCount;
    }

    public static ReadNewsFeedResponseDto toDto(NewsFeed newsFeed, int likeCount) {
        return ReadNewsFeedResponseDto.builder()
                .id(newsFeed.getId())
                .title(newsFeed.getTitle())
                .content(newsFeed.getContent())
                .userId(newsFeed.getUser().getId())
                .createdAt(newsFeed.getCreatedAt())
                .updatedAt(newsFeed.getUpdatedAt())
                .likeCount(likeCount)
                .build();
    }
}
