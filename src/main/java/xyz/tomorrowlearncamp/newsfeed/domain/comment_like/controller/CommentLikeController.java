package xyz.tomorrowlearncamp.newsfeed.domain.comment_like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.dto.CommentLikeRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment_like.service.CommentLikeService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@RestController
@RequestMapping("/comments/{commentsId}/like")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<String> toggleLike(@RequestBody CommentLikeRequestDto requestDto,
                                             @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto responseDto) {
        boolean isLiked = commentLikeService.toggleLike(responseDto.getId(), requestDto.getCommentId());
        return new ResponseEntity<>(isLiked ? "좋아요" : "좋아요 취소", HttpStatus.OK);
    }

}
