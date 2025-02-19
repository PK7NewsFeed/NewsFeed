package xyz.tomorrowlearncamp.newsfeed.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.CreateCommentRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.CreateCommentResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.ReadCommentResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.UpdateCommentResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.entity.Comment;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.repository.CommentRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.service.CommentLikeService;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.service.NewsFeedService;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.service.UsersService;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsFeedService newsFeedService;
    private final CommentLikeService commentLikeService;
    private final UsersService usersService;


    public CreateCommentResponseDto save(CreateCommentRequestDto requestDto, Long userId) {
        int depth = 0;

        Users user = usersService.getUserEntityById(userId);

        NewsFeed newsFeed = newsFeedService.findEntityById(requestDto.getNewsFeedId());

        // parentComment 있는지 확인
        Comment parentComment = null;
        if (requestDto.getParentCommentId() != null) {
            parentComment = commentRepository.findByIdOrElseThrow(requestDto.getParentCommentId());
            depth = parentComment.getDepth() + 1;
        }
        Comment comment = new Comment(user, newsFeed, parentComment, requestDto.getContent(), depth);
        commentRepository.save(comment);
        return new CreateCommentResponseDto(comment);
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
    public UpdateCommentResponseDto update(Long commentId, String newContent, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        // 세션 userId와 수정하려는 댓글의 userId 비교
        if (!userId.equals(comment.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다");
        }

        int likeCount = commentLikeService.getCountCommentLikes(commentId);
        comment.updateContent(newContent);
        return new UpdateCommentResponseDto(
                comment.getId(),
                comment.getUser().getId(),
                comment.getNewsFeed().getId(),
                comment.getUser().getUsername(),
                comment.getNewsFeed().getTitle(),
                comment.getContent(),
                likeCount,
                comment.getParentComment().getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }

    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        // 세션 userId와 삭제하려는 댓글의 userId 비교
        if (!userId.equals(comment.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "삭제 권한이 없습니다");
        }
        commentRepository.delete(comment);
    }
}