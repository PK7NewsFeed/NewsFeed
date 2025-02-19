package xyz.tomorrowlearncamp.newsfeed.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.entity.Friend;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.enums.FriendRequestStatus;

import java.util.List;
import java.util.Optional;


@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
    @Query("SELECT f FROM Friend f WHERE f.receivedUserId = :userId AND f.status = :status")
    List<Friend> findReceivedRequests(@Param("userId") Long userId, @Param("status") FriendRequestStatus status);

    @Query("SELECT f FROM Friend f WHERE (f.requestUserId = :userId OR f.receivedUserId = :userId) AND f.status = :status")
    List<Friend> findAcceptedFriends(@Param("userId") Long userId, @Param("status") FriendRequestStatus status);

    //User1 에서 User2 으로 단방향 검색
    @Query("SELECT f FROM Friend f WHERE (f.requestUserId = :user1 AND f.receivedUserId = :user2) " +
            "AND f.status = :status")
    Optional<Friend> findFriendshipRequest(
            @Param("user1") Long user1,
            @Param("user2") Long user2,
            @Param("status") FriendRequestStatus status
    );

    //User1 에서 User2 로 혹은 User2 에서 User1 으로 양방향 검색
    @Query("SELECT f FROM Friend f WHERE (f.requestUserId = :user1 AND f.receivedUserId = :user2) " +
            "OR (f.requestUserId = :user2 AND f.receivedUserId = :user1) " +
            "AND f.status = :status")
    Optional<Friend> findFriendship(
            @Param("user1") Long user1,
            @Param("user2") Long user2,
            @Param("status") FriendRequestStatus status
    );
}
