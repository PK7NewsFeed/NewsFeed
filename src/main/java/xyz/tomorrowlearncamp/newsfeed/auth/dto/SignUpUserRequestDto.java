package xyz.tomorrowlearncamp.newsfeed.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SignUpUserRequestDto {
    @NotNull(message = "내용이 비어있습니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    @Pattern(regexp = Const.EMAIL_REGEX, message = "이메일 형식이 아닙니다.")
    @Size(min = 8, max = 30)
    private final String email;

    @NotNull
    @Size(min = 8, max = 20)
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "메세지 양식을 확인해주세요.")
    private final String password;

    @NotNull
    @Size(min = 1, max = 10)
    private final String username;

    @NotNull(message = "성별을 선택해주세요.")
    private final Gender gender;

    @NotNull(message = "태어난 날짜를 선택해주세요.")
    private final Timestamp birthDate;
}
