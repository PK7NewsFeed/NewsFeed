package xyz.tomorrowlearncamp.newsfeed.domain.commentlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.commentlike.service.CommentLikeService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@RestController
@RequestMapping("/comments/{commentsId}/like")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<Void> toggleLike(@PathVariable Long commentsId,
                                             @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto responseDto) {
        commentLikeService.toggleLike(responseDto.getId(), commentsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
