package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.domain.user.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SignUpUserRequestDto {
    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Email(message = DtoErrorMessage.INVALID_EMAIL_FORMAT)
    @Pattern(regexp = Const.EMAIL_REGEX, message = DtoErrorMessage.INVALID_EMAIL_FORMAT)
    @Size(min = 8, max = 30)
    private final String email;

    @NotNull
    @Size(min = 8, max = 20)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = DtoErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String password;

    @NotNull
    @Size(min = 1, max = 10)
    private final String username;

    @NotNull(message = DtoErrorMessage.MISSING_GENDER)
    private final Gender gender;

    @NotNull(message = DtoErrorMessage.MISSING_BIRTHDATE)
    private final LocalDate birthDate;
}
