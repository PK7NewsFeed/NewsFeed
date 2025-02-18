package xyz.tomorrowlearncamp.newsfeed.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.friend.enums.FriendRequestStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
    Optional<Friend> findByRequestUserAndReceivedUserAndStatus(Users requestUser, Users receivedUser, FriendRequestStatus status);
    List<Friend> findByReceivedUserAndStatus(Users receivedUser, FriendRequestStatus status);
    List<Friend> findByRequestUserAndStatus(Users requestUser, FriendRequestStatus status);
}
