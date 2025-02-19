package xyz.tomorrowlearncamp.newsfeed.domain.commentlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.commentlike.service.CommentLikeService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

@RestController
@RequestMapping("/comments/{commentsId}/like")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Void> toggleLike(@PathVariable Long commentsId,
                                           @RequestHeader(JwtProperties.HEADER_STRING) String token) {
        Long userId = jwtUtil.extractUserId(token);
        commentLikeService.toggleLike(userId, commentsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
