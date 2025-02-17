package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.Entity.CommentLike;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByCommentAndUser(Comment comment, Users user);

    void deleteByCommentAndUser(Comment comment, Users user);
}
