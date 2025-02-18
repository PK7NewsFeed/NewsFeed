package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;

public interface NewsFeedRepository extends JpaRepository<NewsFeed,Long> {
        }
