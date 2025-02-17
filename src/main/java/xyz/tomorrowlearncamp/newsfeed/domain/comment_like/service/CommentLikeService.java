package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.repository.CommentRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.repository.CommentLikeRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public boolean toggleLike(Long userId, Long commentId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        boolean isLiked = commentLikeRepository.existsByCommentAndUser(comment, user);

        commentLikeRepository.toggle(comment, user);

        return !isLiked;
    }


}
