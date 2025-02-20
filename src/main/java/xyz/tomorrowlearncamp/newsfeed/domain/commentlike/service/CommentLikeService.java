package xyz.tomorrowlearncamp.newsfeed.domain.commentlike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.repository.CommentRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.commentlike.repository.CommentLikeRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundCommentException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.SelfLikeNotAllowedException;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void toggleLike(Long userId, Long commentId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        if (comment.getUser().equals(user)) {
            throw new SelfLikeNotAllowedException();
        }

        if (comment.isDeleted()) {
            throw new NotFoundCommentException();
        }


        commentLikeRepository.toggle(comment, user);
    }

    public int getCountCommentLikes(Long commentId) {
        return commentLikeRepository.countCommentLikeByCommentId(commentId);
    }
}
