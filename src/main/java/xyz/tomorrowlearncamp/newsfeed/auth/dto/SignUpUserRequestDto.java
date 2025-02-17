package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SignUpUserRequestDto {

    @NotNull(message = "내용이 비어있습니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    @Pattern(regexp = Const.EMAIL_REGEX, message = "이메일 형식이 아닙니다.")
    @Size(min = 10, max = 30)
    private String email;

    @NotNull
    @Size(min = 8, max = 20)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "메세지 양식을 확인해주세요.")
    private String password;

    @NotNull
    @Size(min = 1, max = 10)
    private String username;
}
