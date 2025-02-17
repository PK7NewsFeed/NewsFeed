package xyz.tomorrowlearncamp.newsfeed.friend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.tomorrowlearncamp.newsfeed.friend.enumerator.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.friend.temp.User;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "friends")
public class Friend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_user")
    private User requestUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_user")
    private User receivedUser;

    @Setter
    private FriendRequestStatus status;

    public Friend(User requestUser, User receivedUser, FriendRequestStatus status) {
        requestUser = requestUser;
        receivedUser = receivedUser;
        this.status = status;
    }
}
