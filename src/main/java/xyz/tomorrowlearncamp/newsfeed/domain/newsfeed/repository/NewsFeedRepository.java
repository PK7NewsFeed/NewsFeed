package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;

import java.time.LocalDateTime;

public interface NewsFeedRepository extends JpaRepository<NewsFeed,Long> {
    Page<NewsFeed> findAllByCreatedAtBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1, Pageable pageable);

    Page<NewsFeed> findAllByCreatedAtAfter(LocalDateTime localDateTime, Pageable pageable);

    Page<NewsFeed> findAllByCreatedAtBefore(LocalDateTime localDateTime, Pageable pageable);
}
