package xyz.tomorrowlearncamp.newsfeed.friend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.friend.enums.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.friend.service.FriendService;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping
    public ResponseEntity<String> addFriend(
            @RequestParam Long userId,
            HttpServletRequest request
    ) {
        Long requestUserId = (Long) request.getAttribute("userId");
        friendService.sendFriendRequest(requestUserId, userId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping
    public List<Users> getFriendRequests(
            @RequestParam FriendRequestStatus status,
            HttpServletRequest request
    ) {
        Long userId = (Long) request.getAttribute("userId");
        return friendService.getFriendRequest(userId, status);
    }
}
