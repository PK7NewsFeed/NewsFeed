package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UpdateUsersRequestDto {
    private final String userName;

    private final String email;

    private final Gender gender;

    private final Timestamp birthDate;

}
