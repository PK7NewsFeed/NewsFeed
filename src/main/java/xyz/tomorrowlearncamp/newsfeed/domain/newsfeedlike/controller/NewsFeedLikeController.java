package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.dto.NewsFeedLikeRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service.NewsFeedLikeService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsFeedLikeController {

    private final NewsFeedLikeService newsFeedLikeService;

    @PostMapping("/{newsfeedsId}/like")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long newsfeedsId,
            @SessionAttribute(name =Const.LOGIN_USER)LoginUserResponseDto loginUser
    ) {
        boolean isLiked = newsFeedLikeService.toggleLike(newsfeedsId, loginUser.getId());
        return new ResponseEntity<>(isLiked ? "좋아요" : "좋아요 취소", HttpStatus.OK);
    }
}
