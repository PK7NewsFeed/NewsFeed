package xyz.tomorrowlearncamp.newsfeed.domain.comment.service;

import lombok.RequiredArgsConstructor;
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
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.repository.NewsFeedRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundNewsFeedException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final NewsFeedRepository newsFeedRepository;


    public CreateCommentResponseDto save(CreateCommentRequestDto requestDto, Long sessionUserId) {
        int depth = 0;

        Users user = usersRepository.findById(sessionUserId)
                .orElseThrow(NotFoundUserException::new);

        NewsFeed newsFeed = newsFeedRepository.findById(requestDto.getNewsFeedId())
                .orElseThrow(NotFoundNewsFeedException::new);

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
    public List<ReadCommentResponseDto> getCommentsByNewsFeedId(Long newsFeedId) {
        List<Comment> comments = commentRepository.findByNewsFeedId(newsFeedId);
        return comments.stream()
                .map(ReadCommentResponseDto::toDto)
                .toList();
    }

    @Transactional
    public UpdateCommentResponseDto update(Long commentId, String newContent, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        // 세션 userId와 수정하려는 댓글의 userId 비교
        if (!userId.equals(comment.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다");
        }

        comment.updateContent(newContent);
        return UpdateCommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .updatedAt(comment.getUpdatedAt())
                .createdAt(comment.getCreatedAt())
                .feedId(comment.getNewsFeed().getId())
                .feedname(comment.getNewsFeed().getTitle())
                .parentCommentId(comment.getParentComment().getId())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .build();
    }

    @Transactional
    public String delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        // 세션 userId와 삭제하려는 댓글의 userId 비교
        if (!userId.equals(comment.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "삭제 권한이 없습니다");
        }

        commentRepository.delete(comment);
        return "댓글이 삭제되었습니다";
    }
}