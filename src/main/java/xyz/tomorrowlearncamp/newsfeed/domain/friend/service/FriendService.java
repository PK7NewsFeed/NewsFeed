package xyz.tomorrowlearncamp.newsfeed.domain.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.dto.UserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.enums.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.repository.FriendRepository;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public void sendFriendRequest(Long requestUserId, Long receivedUserId) {
        if(requestUserId.equals(receivedUserId)){
            throw new NotFoundUserException();
        }

        Users requestUser = usersRepository.findById(requestUserId)
                .orElseThrow(NotFoundUserException::new);

        Users receivedUser = usersRepository.findById(receivedUserId)
                .orElseThrow(NotFoundUserException::new);

        Optional<Friend> existingRequest = friendRepository.findByRequestUserIdAndReceivedUserIdAndStatus(
                receivedUser.getId(), requestUser.getId(), FriendRequestStatus.WAITING
        );

        Friend friendRequest;

        if(existingRequest.isPresent()) {
            friendRequest = existingRequest.get();
            friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
        } else {
            friendRequest = new Friend(requestUser.getId(), receivedUser.getId(), FriendRequestStatus.WAITING);
        }
        friendRepository.save(friendRequest);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getFriendRequest(Long userId, String status) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        List<Long> ResponseList = new ArrayList<>();

        if(status.equals("WAITING")) {
            ResponseList = friendRepository.findByReceivedUserIdAndStatus(user.getId(), FriendRequestStatus.WAITING)
                    .stream()
                    .map(Friend::getRequestUserId)
                    .toList();
        } else if (status.equals("ACCEPTED")) {
            List<Long> receivedUsers = friendRepository.findByRequestUserIdAndStatus(user.getId(), FriendRequestStatus.ACCEPTED)
                    .stream()
                    .map(Friend::getReceivedUserId)
                    .toList();
            List<Long> requestUsers = friendRepository.findByReceivedUserIdAndStatus(user.getId(), FriendRequestStatus.ACCEPTED)
                    .stream()
                    .map(Friend::getRequestUserId)
                    .toList();
            ResponseList.addAll(receivedUsers);
            ResponseList.addAll(requestUsers);
        } else {
            throw new NotFoundUserException();
        }

        List<Users> usersList = usersRepository.findAllById(ResponseList);
        return usersList.stream()
                .map(users -> new UserResponseDto(users.getId(), users.getUsername(), users.getEmail()))
                .toList();
    }
}
