package xyz.tomorrowlearncamp.newsfeed.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.CreateCommentRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.CreateCommentResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.ReadCommentResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.UpdateCommentResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.repository.CommentRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.commentlike.service.CommentLikeService;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.service.NewsFeedService;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.service.UsersService;
import xyz.tomorrowlearncamp.newsfeed.global.exception.LoginUserException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundCommentException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.UnauthorizedWriterException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsFeedService newsFeedService;
    private final CommentLikeService commentLikeService;
    private final UsersService usersService;


    public CreateCommentResponseDto saveComment(CreateCommentRequestDto requestDto, Long userId) {
        int depth = 0;

        Users user = usersService.getUserEntityById(userId);

        NewsFeed newsFeed = newsFeedService.getNewsFeedById(requestDto.getNewsFeedId());

        // parentComment 있는지 확인
        Comment parentComment = null;
        if (requestDto.getParentCommentId() != null) {
            parentComment = commentRepository.findByIdOrElseThrow(requestDto.getParentCommentId());
            depth = parentComment.getDepth() + 1;
        }
        Comment comment = Comment.builder()
                .user(user)
                .newsFeed(newsFeed)
                .parentComment(parentComment)
                .content(requestDto.getContent())
                .depth(depth)
                .build();
        commentRepository.save(comment);
        return CreateCommentResponseDto.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .feedId(comment.getNewsFeed().getId())
                .content(comment.getContent())
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .username(comment.getUser().getUsername())
                .feedname(comment.getNewsFeed().getTitle())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ReadCommentResponseDto> getCommentsByNewsFeedId(Long newsFeedId, Pageable pageable) {

        Page<Comment> comments = commentRepository.findByNewsFeedId(newsFeedId, pageable);
        return comments
                .map(comment ->
                {
                    int likeCount = commentLikeService.getCountCommentLikes(comment.getId());
                    return ReadCommentResponseDto.toDto(comment, likeCount);
                });
    }

    @Transactional
    public UpdateCommentResponseDto updateComment(Long commentId, String newContent, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        // 세션 userId와 수정하려는 댓글의 userId 비교
        if (!userId.equals(comment.getUser().getId())) {
            throw new LoginUserException();
        }

        int likeCount = commentLikeService.getCountCommentLikes(commentId);
        comment.updateContent(newContent);
        return UpdateCommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .updatedAt(comment.getUpdatedAt())
                .createdAt(comment.getCreatedAt())
                .feedId(comment.getNewsFeed().getId())
                .feedname(comment.getNewsFeed().getTitle())
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .likeCount(likeCount)
                .build();
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        // 세션 userId와 삭제하려는 댓글의 userId 비교
        if (!userId.equals(comment.getUser().getId()) || !userId.equals(comment.getNewsFeed().getUser().getId())) {
            throw new UnauthorizedWriterException();
        }
        commentRepository.delete(comment);
    }
}