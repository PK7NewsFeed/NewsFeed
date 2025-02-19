package xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.domain.user.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UpdateUsersRequestDto {
    private final String username;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Pattern(regexp = Const.EMAIL_REGEX, message = DtoErrorMessage.INVALID_EMAIL_FORMAT)
    @Email(message = DtoErrorMessage.INVALID_EMAIL_FORMAT)
    @Size(min = 10, max = 30, message = DtoErrorMessage.INVALID_EMAIL_LENGTH)
    private final String email;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 8, max = 20, message = DtoErrorMessage.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = DtoErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String password;

    @NotNull(message = DtoErrorMessage.MISSING_GENDER)
    private final Gender gender;

    @NotNull(message = DtoErrorMessage.MISSING_BIRTHDATE)
    private final LocalDate birthDate;

}
