package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.dto.NewsFeedLikeRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service.NewsFeedLikeService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsFeedLikeController {

    private final NewsFeedLikeService newsFeedLikeService;

    @PostMapping("/{newsfeedsId}/like")
    public ResponseEntity<Void> toggleLike(
            @PathVariable Long newsfeedsId,
            @SessionAttribute(name =Const.LOGIN_USER)LoginUserResponseDto loginUser
    ) {
        newsFeedLikeService.toggleLike(newsfeedsId, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/like")
//    public ResponseEntity<List<ReadNewsFeedResponseDto>> getLikeNewsFeeds(
//            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
//    ) {
//
//        return new ResponseEntity<>(newsFeedLikeService.getLikeNewsFeeds(loginUser.getId()), HttpStatus.OK);
//    }
}
