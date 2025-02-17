package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UpdateUsersResponseDto {
    private Long id;

    private String userName;

    private String email;

    private Gender gender;

    private Timestamp birthDate;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
