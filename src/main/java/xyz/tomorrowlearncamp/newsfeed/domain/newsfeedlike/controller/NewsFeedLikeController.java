package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.dto.responseDto.ReadNewsFeedResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.service.NewsFeedLikeService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsFeedLikeController {

    private final NewsFeedLikeService newsFeedLikeService;

    private final JwtUtil jwtUtil;

    @PostMapping("/{newsfeedsId}/like")
    public ResponseEntity<Void> toggleLike(
            @PathVariable Long newsfeedsId,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long userId = jwtUtil.extractUserId(token);
        newsFeedLikeService.toggleLike(newsfeedsId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<List<ReadNewsFeedResponseDto>> getLikeNewsFeeds(
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long userId = jwtUtil.extractUserId(token);
        return new ResponseEntity<>(newsFeedLikeService.getLikeNewsFeeds(userId), HttpStatus.OK);
    }
}
