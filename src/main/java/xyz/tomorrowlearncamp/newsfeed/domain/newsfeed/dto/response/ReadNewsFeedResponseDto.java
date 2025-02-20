package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.response;

import lombok.Builder;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;

import java.time.LocalDateTime;

@Getter
public class ReadNewsFeedResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;
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
        this.userId = userId;
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
