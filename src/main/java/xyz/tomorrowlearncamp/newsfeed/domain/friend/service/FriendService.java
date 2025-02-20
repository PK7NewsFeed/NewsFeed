package xyz.tomorrowlearncamp.newsfeed.domain.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.dto.UserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.service.UsersService;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.enums.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.repository.FriendRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UsersService usersService;

    @Transactional
    public void sendFriendRequest(Long requestUserId, Long receivedUserId) {

        if (requestUserId.equals(receivedUserId)) {
            throw new NotFoundUserException();
        }
        usersService.validateUserExists(requestUserId);
        usersService.validateUserExists(receivedUserId);

        Optional<Friend> existingRequest = friendRepository.findFriendshipRequest(
                receivedUserId, requestUserId, FriendRequestStatus.WAITING
        );

        Friend friendRequest;

        if (existingRequest.isPresent()) {
            friendRequest = existingRequest.get();
            friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
        } else {
            friendRequest = Friend.builder()
                    .requestUserId(requestUserId)
                    .receivedUserId(receivedUserId)
                    .status(FriendRequestStatus.WAITING)
                    .build();
        }
        friendRepository.save(friendRequest);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getFriendRequest(Long userId, String status) {
        usersService.validateUserExists(userId);

        List<Long> responseList = switch (FriendRequestStatus.valueOf(status)) {
            case WAITING -> friendRepository.findReceivedRequests(userId, FriendRequestStatus.WAITING)
                    .stream()
                    .map(Friend::getRequestUserId)
                    .toList();
            case ACCEPTED -> friendRepository.findAcceptedFriends(userId, FriendRequestStatus.ACCEPTED)
                    .stream()
                    .map(friend -> friend.getRequestUserId().equals(userId) ? friend.getReceivedUserId() : friend.getRequestUserId())
                    .toList();
            default -> throw new NotFoundUserException();
        };

        return responseList.stream()
                .map(id -> {
                    ReadUsersResponseDto user = usersService.getUserById(id);
                    return UserResponseDto.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build();
                })
                .toList();
    }

    public void deleteFriend(Long requestUserId, Long userId) {
        Friend friend = friendRepository.findFriendship(requestUserId, userId, FriendRequestStatus.ACCEPTED).orElseThrow(NotFoundUserException::new);

        friendRepository.delete(friend);
    }
}
