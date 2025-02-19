package xyz.tomorrowlearncamp.newsfeed.domain.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.dto.UserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.service.FriendService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;
import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Void> saveFriend(
            @RequestParam Long userId,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long requestUserId = jwtUtil.extractUserId(token);
        friendService.sendFriendRequest(requestUserId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<UserResponseDto> getFriendRequests(
            @RequestParam String status,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long requestUserId = jwtUtil.extractUserId(token);
        return friendService.getFriendRequest(requestUserId, status);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFriend(
            @RequestParam Long userId,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long requestUserId = jwtUtil.extractUserId(token);
        friendService.delete(requestUserId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
