package xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.user.enums.Gender;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UpdateUsersResponseDto {
    private Long id;

    private String username;

    private String email;

    private Gender gender;

    private LocalDate birthDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder()
    public UpdateUsersResponseDto(Long id, String email, String username, Gender gender, LocalDate birthDate, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
