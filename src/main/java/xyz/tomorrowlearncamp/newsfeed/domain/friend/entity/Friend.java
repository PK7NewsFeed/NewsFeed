package xyz.tomorrowlearncamp.newsfeed.domain.friend.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private Long requestUserId;
    private Long receivedUserId;

    @Setter
    private FriendRequestStatus status;

    @Builder
    public Friend(Long requestUserId, Long receivedUserId, FriendRequestStatus status) {
        this.requestUserId = requestUserId;
        this.receivedUserId = receivedUserId;
        this.status = status;
    }
}
