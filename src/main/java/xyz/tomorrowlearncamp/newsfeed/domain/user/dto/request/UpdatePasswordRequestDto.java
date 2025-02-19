package xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;
import xyz.tomorrowlearncamp.newsfeed.global.etc.DtoErrorMessage;

@AllArgsConstructor
@Getter
public class UpdatePasswordRequestDto {
    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 8, max = 20, message = DtoErrorMessage.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = DtoErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String oldPassword;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 8, max = 20, message = DtoErrorMessage.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = DtoErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String newPassword;

    @NotNull(message = DtoErrorMessage.EMPTY_CONTENT)
    @Size(min = 8, max = 20, message = DtoErrorMessage.INVALID_PASSWORD_LENGTH)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = DtoErrorMessage.INVALID_PASSWORD_FORMAT)
    private final String newPasswordCheck;
}
