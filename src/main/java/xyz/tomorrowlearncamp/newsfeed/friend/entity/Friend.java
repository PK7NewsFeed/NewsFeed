package xyz.tomorrowlearncamp.newsfeed.friend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.friend.enums.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "friends")
public class Friend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestUserId;
    private Long receivedUserId;

    @Setter
    private FriendRequestStatus status;

    public Friend(Long requestUserId, Long receivedUserId, FriendRequestStatus status) {
        this.requestUserId = requestUserId;
        this.receivedUserId = receivedUserId;
        this.status = status;
    }
}
