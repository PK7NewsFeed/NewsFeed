package xyz.tomorrowlearncamp.newsfeed.domain.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다"));
    }

    @EntityGraph(attributePaths = {"user"})
    Page<Comment> findByNewsFeedId(Long newsFeedId, Pageable pageable);
}
