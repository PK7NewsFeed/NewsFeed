package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.entity.NewsFeedLike;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;

import java.util.List;

public interface NewsFeedLikeRepository extends JpaRepository<NewsFeedLike, Long> {
    boolean existsByNewsFeedAndUser(NewsFeed newsFeed, Users user);

    void deleteByNewsFeedAndUser(NewsFeed newsFeed, Users user);

    default void toggle(NewsFeed newsFeed, Users user) {
        if (existsByNewsFeedAndUser(newsFeed, user)) {
            deleteByNewsFeedAndUser(newsFeed, user);
        } else {
            save(NewsFeedLike.builder()
                    .user(user)
                    .newsFeed(newsFeed)
                    .build());
        }
    }

    int countNewsFeedLikeByNewsFeedId(Long newsFeedId);


    List<NewsFeedLike> findByUser(Users user);
}
