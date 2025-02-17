package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdatePasswordRequestDto {
    private final String oldPassword;

    private final String newPassword;

    private final String newPasswordCheck;
}
