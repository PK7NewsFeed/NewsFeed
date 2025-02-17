package xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@AllArgsConstructor
@Getter
public class UpdatePasswordRequestDto {

    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 8, max = 20, message = "크기가 맞지 않습니다! 비밀번호는 7보다 크고 21보다 작아야합니다!")
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "전 비밀번호 양식을 확인해주세요.")
    private final String oldPassword;

    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 8, max = 20, message = "크기가 맞지 않습니다! 비밀번호는 7보다 크고 21보다 작아야합니다!")
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "새 비밀번호 양식을 확인해주세요.")
    private final String newPassword;

    @NotNull(message = "내용을 입력해주세요.")
    @Size(min = 8, max = 20, message = "크기가 맞지 않습니다! 비밀번호는 7보다 크고 21보다 작아야합니다!")
    @Pattern(regexp = Const.PASSWORD_REGEX, message = "새 비밀번화 확인 양식을 확인해주세요.")
    private final String newPasswordCheck;
}
