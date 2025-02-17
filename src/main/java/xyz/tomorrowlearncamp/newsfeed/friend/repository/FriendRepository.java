package xyz.tomorrowlearncamp.newsfeed.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.tomorrowlearncamp.newsfeed.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.friend.temp.User;
import xyz.tomorrowlearncamp.newsfeed.friend.enumerator.FriendRequestStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
    Optional<Friend> findByRequestUserAndReceivedUserAndStatus(User requestUser, User receivedUser, FriendRequestStatus status);
    List<Friend> findByReceivedUserAndStatus(User requestUser, FriendRequestStatus status);
    @Query("SELECT f FROM Friend f WHERE (f.requestUser = :user OR f.receivedUser = :user) AND f.status = :status")
    List<Friend> findAcceptedFriends(@Param("user") User user, @Param("status") FriendRequestStatus status);
}
