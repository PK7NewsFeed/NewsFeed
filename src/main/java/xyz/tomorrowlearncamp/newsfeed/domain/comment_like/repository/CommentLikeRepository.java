package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.Entity.CommentLike;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    boolean existsByCommentAndUser(Comment comment, Users user);

    void deleteByCommentAndUser(Comment comment, Users user);

    default void toggle(Comment comment, Users user) {
        if (existsByCommentAndUser(comment, user)) {
            deleteByCommentAndUser(comment, user);
        } else {
            save(new CommentLike(user, comment));
        }
    }
}
