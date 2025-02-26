package xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.user.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ReadUsersResponseDto {
    private final Long id;

    private final String username;

    private final String email;

    private final Gender gender;

    private final LocalDate birthDate;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    @Builder()
    public ReadUsersResponseDto(Long id, String email, String username, Gender gender, LocalDate birthDate, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
