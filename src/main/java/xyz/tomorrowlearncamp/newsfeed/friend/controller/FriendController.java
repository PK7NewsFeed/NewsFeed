package xyz.tomorrowlearncamp.newsfeed.friend.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.friend.dto.request.AddFriendRequestDto;
import xyz.tomorrowlearncamp.newsfeed.friend.dto.response.AddFriendResponseDto;
import xyz.tomorrowlearncamp.newsfeed.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.friend.enumerator.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.friend.service.FriendService;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping
    public AddFriendResponseDto addFriend(
            @RequestBody AddFriendRequestDto dto,
            HttpServletRequest request
    ) {
        Long requestUserId = (Long) request.getAttribute("userId");
        return friendService.sendFriendRequest(requestUserId, dto);
    }

    @GetMapping
    public List<Friend> getFriendRequests(
            @RequestParam FriendRequestStatus status,
            HttpServletRequest request
    ) {
        Long userId = (Long) request.getAttribute("userId");
        return friendService.getFriendRequest(userId, status);
    }
}
