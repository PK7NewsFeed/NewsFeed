package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UpdateUsersRequestDto {
    private final String userName;

    @NotNull(message = "내용이 비어있습니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    @Pattern(regexp = Const.EMAIL_REGEX, message = "이메일 형식이 아닙니다.")
    @Size(min = 10, max = 30, message = "길거나 짧습니다.")
    private final String email;

    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 8, max = 20, message = "크기가 맞지 않습니다! 비밀번호는 7보다 크고 21보다 작아야합니다!")
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "전 비밀번호 양식을 확인해주세요.")
    private final String password;

    @NotNull(message = "성별을 선택해주세요.")
    private final Gender gender;

    @NotNull(message = "태어난 날짜를 선택해주세요.")
    private final Timestamp birthDate;

}
