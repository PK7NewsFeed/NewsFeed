package xyz.tomorrowlearncamp.newsfeed.domain.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        Users requestUser = usersRepository.findById(requestUserId)
                .orElseThrow(NotFoundUserException::new);

        Users receivedUser = usersRepository.findById(receivedUserId)
                .orElseThrow(NotFoundUserException::new);

        Optional<Friend> existingRequest = friendRepository.findByRequestUserAndReceivedUserAndStatus(
                receivedUser, requestUser, FriendRequestStatus.WAITING
        );

        Friend friendRequest;

        if(existingRequest.isPresent()) {
            friendRequest = existingRequest.get();
            friendRequest.setStatus(FriendRequestStatus.ACCEPTED);
        } else {
            friendRequest = new Friend(requestUser, receivedUser, FriendRequestStatus.WAITING);
        }
        friendRepository.save(friendRequest);
    }

    @Transactional(readOnly = true)
    public List<Users> getFriendRequest(Long userId, FriendRequestStatus status) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        if(status == FriendRequestStatus.WAITING) {
            return friendRepository.findByReceivedUserAndStatus(user, status)
                    .stream()
                    .map(Friend::getRequestUser)
                    .toList();
        } else if (status == FriendRequestStatus.ACCEPTED) {
            List<Users> receivedUsers = new ArrayList<>(friendRepository.findByRequestUserAndStatus(user, status)
                    .stream()
                    .map(Friend::getReceivedUser)
                    .toList());
            List<Users> requestUsers = friendRepository.findByReceivedUserAndStatus(user, status)
                    .stream()
                    .map(Friend::getRequestUser)
                    .toList();
            receivedUsers.addAll(requestUsers);
            return receivedUsers;
        } else {
            throw new NotFoundUserException();
        }
    }
}
