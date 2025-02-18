package xyz.tomorrowlearncamp.newsfeed.domain.friend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.friend.enums.FriendRequestStatus;
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
    private Users requestUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_user")
    private Users receivedUser;

    @Setter
    private FriendRequestStatus status;

    public Friend(Users requestUser, Users receivedUser, FriendRequestStatus status) {
        requestUser = requestUser;
        receivedUser = receivedUser;
        this.status = status;
    }
}
