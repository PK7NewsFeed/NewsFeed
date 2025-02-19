package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.entity.NewsFeedLike;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;

import java.util.List;

public interface NewsFeedLikeRepository extends JpaRepository<NewsFeedLike, Long> {
    boolean existsByNewsFeedAndUser(NewsFeed newsFeed, Users user);

    void deleteByNewsFeedAndUser(NewsFeed newsFeed, Users user);

    default void toggle(NewsFeed newsFeed, Users user) {
        if (existsByNewsFeedAndUser(newsFeed, user)) {
            deleteByNewsFeedAndUser(newsFeed, user);
        } else {
            save(new NewsFeedLike(user, newsFeed));
        }
    }

    int countNewsFeedLikeByNewsFeedId(Long newsFeedId);

//    List<ReadNewsFeedResponseDto> findNewsFeedByUserId(Long userId);
}
