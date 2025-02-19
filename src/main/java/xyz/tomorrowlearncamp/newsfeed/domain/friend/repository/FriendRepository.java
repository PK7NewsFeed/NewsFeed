package xyz.tomorrowlearncamp.newsfeed.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.enums.FriendRequestStatus;

import java.util.List;
import java.util.Optional;


@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
    Optional<Friend> findByRequestUserIdAndReceivedUserIdAndStatus(Long requestUserId, Long receivedUserId, FriendRequestStatus status);
    List<Friend> findByReceivedUserIdAndStatus(Long receivedUserId, FriendRequestStatus status);
    List<Friend> findByRequestUserIdAndStatus(Long requestUserId, FriendRequestStatus status);
}
