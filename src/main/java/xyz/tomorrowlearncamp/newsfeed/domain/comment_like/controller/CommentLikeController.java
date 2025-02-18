package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.dto.CommentLikeRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.service.CommentLikeService;

@RestController
@RequestMapping("/comments/{commentsId}/like")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<String> toggleLike(@RequestBody CommentLikeRequestDto requestDto) {
        boolean isLiked = commentLikeService.toggleLike(requestDto.getUserId(), requestDto.getCommentId());
        return new ResponseEntity<>(isLiked ? "좋아요" : "좋아요 취소", HttpStatus.OK);
    }

}
