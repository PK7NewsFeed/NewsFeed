package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUsersRequestDto {
    private final String password;
}
