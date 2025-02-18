package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.etc.ErrorMessage;

@Getter
@RequiredArgsConstructor
public class LoginUserRequestDto {

    @NotNull(message = ErrorMessage.EMPTY_CONTENT)
    @Pattern(regexp = Const.EMAIL_REGEX, message = ErrorMessage.INVALID_EMAIL_FORMAT)
    @Size(min = 8, max = 30, message = ErrorMessage.INVALID_EMAIL_LENGTH)
    private final String email;

    @NotNull(message = ErrorMessage.EMPTY_CONTENT)
    @Size(min = 8, max = 20, message = ErrorMessage.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = ErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String password;
}
