package xyz.tomorrowlearncamp.newsfeed.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.friend.dto.request.AddFriendRequestDto;
import xyz.tomorrowlearncamp.newsfeed.friend.dto.response.AddFriendResponseDto;
import xyz.tomorrowlearncamp.newsfeed.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.friend.enumerator.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.friend.repository.FriendRepository;
import xyz.tomorrowlearncamp.newsfeed.friend.temp.User;
import xyz.tomorrowlearncamp.newsfeed.friend.temp.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public AddFriendResponseDto sendFriendRequest(Long requestUserId, AddFriendRequestDto dto) {
        User requestUser = userRepository.findById(requestUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자가 존재하지 않습니다."));

        User receivedUser = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("추가하려는 친구가 존재하지 않습니다."));

        Optional<Friend> existingRequest = friendRepository.findByRequestUserAndReceivedUserAndStatus(
                receivedUser, requestUser, FriendRequestStatus.WAITING
        );

        if(existingRequest.isPresent()) {
            Friend friendRequest = existingRequest.get();
            friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
            friendRepository.save(friendRequest);
            return new AddFriendResponseDto(requestUser, receivedUser, FriendRequestStatus.ACCEPTED);
        } else {
            Friend friendRequest = new Friend(requestUser, receivedUser, FriendRequestStatus.WAITING);
            friendRepository.save(friendRequest);
            return new AddFriendResponseDto(requestUser, receivedUser, FriendRequestStatus.WAITING);
        }
    }

    @Transactional(readOnly = true)
    public List<Friend> getFriendRequest(Long userId, FriendRequestStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 사용자가 존재하지 않습니다."));

        List<Friend> friendList;

        if(status == FriendRequestStatus.WAITING) {
            friendList = friendRepository.findByReceivedUserAndStatus(user, status);
        } else if (status == FriendRequestStatus.ACCEPTED) {
            friendList = friendRepository.findAcceptedFriends(user, status);
        } else {
            throw new IllegalArgumentException("유효하지 않은 친구 요청 상태입니다.");
        }

        return friendList.stream()
                .map(friend -> new Friend(friend.getRequestUser(), friend.getReceivedUser(), friend.getStatus())).toList();
    }
}
