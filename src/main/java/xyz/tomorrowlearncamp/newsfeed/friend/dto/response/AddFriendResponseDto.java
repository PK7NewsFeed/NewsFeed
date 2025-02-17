package xyz.tomorrowlearncamp.newsfeed.friend.dto.response;

import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.friend.enumerator.FriendRequestStatus;
import xyz.tomorrowlearncamp.newsfeed.friend.temp.User;

@Getter
public class AddFriendResponseDto {

    private final User RequestUser;
    private final User RecievedUser;
    private final FriendRequestStatus status;

    public AddFriendResponseDto(User requestUser, User recievedUser, FriendRequestStatus status) {
        RequestUser = requestUser;
        RecievedUser = recievedUser;
        this.status = status;
    }
}
