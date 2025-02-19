package xyz.tomorrowlearncamp.newsfeed.domain.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.dto.UserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.service.FriendService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping
    public ResponseEntity<Void> addFriend(
            @RequestParam Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
    ) {
        Long requestUserId = loginUser.getId();
        friendService.sendFriendRequest(requestUserId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<UserResponseDto> getFriendRequests(
            @RequestParam String status,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
    ) {
        Long requestUserId = loginUser.getId();
        return friendService.getFriendRequest(requestUserId, status);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFriend(
            @RequestParam Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
    ) {
        Long requestUserId = loginUser.getId();
        friendService.delete(requestUserId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
