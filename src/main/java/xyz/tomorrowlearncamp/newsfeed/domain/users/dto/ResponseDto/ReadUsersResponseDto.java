package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;

import java.sql.Timestamp;

@Getter
public class ReadUsersResponseDto {

    private Long id;

    private String username;

    private String email;

    private Gender gender;

    private Timestamp birthDate;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @Builder()
    public ReadUsersResponseDto(Long id, String email, String username, Gender gender, Timestamp birthDate, Timestamp createdAt, Timestamp updatedAt){
        this.id = id;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
