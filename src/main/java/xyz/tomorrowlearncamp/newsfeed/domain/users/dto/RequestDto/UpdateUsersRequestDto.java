package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.etc.ErrorMessage;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UpdateUsersRequestDto {
    private final String username;

    @NotNull(message = ErrorMessage.EMPTY_CONTENT)
    @Pattern(regexp = Const.EMAIL_REGEX, message = ErrorMessage.INVALID_EMAIL_FORMAT)
    @Email(message = ErrorMessage.INVALID_EMAIL_FORMAT)
    @Size(min = 10, max = 30, message = ErrorMessage.INVALID_EMAIL_LENGTH)
    private final String email;

    @NotNull(message = ErrorMessage.EMPTY_CONTENT)
    @Size(min = 8, max = 20, message = ErrorMessage.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = ErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String password;

    @NotNull(message = ErrorMessage.MISSING_GENDER)
    private final Gender gender;

    @NotNull(message = ErrorMessage.MISSING_BIRTHDATE)
    private final Timestamp birthDate;

}
