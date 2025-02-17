package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@Getter
@RequiredArgsConstructor
public class LoginUserRequestDto {

    @NotNull(message = "내용이 비어있습니다.")
    @Pattern(regexp = Const.EMAIL_REGEX, message = "이메일 형식이 아닙니다.")
    @Size(min = 8, max = 30, message = "길거나 짧습니다.")
    private final String email;

    @NotNull(message = "내용이 비어있습니다.")
    @Size(min = 8, max = 20, message = "길거나 짧습니다.")
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "메세지 양식을 확인해주세요.")
    private final String password;
}
