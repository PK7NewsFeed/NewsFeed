package xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;

import java.time.LocalDateTime;

public interface NewsFeedRepository extends JpaRepository<NewsFeed,Long> {
    @Query("SELECT nf, COUNT(nfl) as likes " +
            "FROM NewsFeed nf " +
            "LEFT JOIN NewsFeedLike nfl ON nf.id = nfl.newsFeed.id " +
            "WHERE nf.deleted = false " +
            "AND (:startDate IS NULL OR nf.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR nf.createdAt <= :endDate) " +
            "GROUP BY nf.id")
    Page<Object[]> findAllWithLikeCount(@Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate,
                                        Pageable pageable);
}
