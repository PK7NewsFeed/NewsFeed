package xyz.tomorrowlearncamp.newsfeed.auth.Controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.tomorrowlearncamp.newsfeed.auth.Service.AuthService;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.*;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "인증/인가 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signUp(
            @Validated @RequestBody SignUpUserRequestDto requestDto
    ) {

        SignUpUserResponseDto responseDto = authService.signUp(requestDto.getEmail(), requestDto.getPassword(), requestDto.getUsername());

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(
            @Validated @RequestBody LoginUserRequest requestDto,
            HttpServletRequest httpServletRequest
    ) {
        // 유저 정보 확인
        LoginUserResponse responseDto = authService.login(requestDto.getEmail(), requestDto.getPassword());

        // 세션 설정
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(Const.LOGIN_USER, responseDto);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutUserResponse> logout(
            HttpServletRequest httpServletRequest
    ) {
        // 기존 세션 확인
        HttpSession session = httpServletRequest.getSession(false);

        if (session != null) { // 세션이 있다면
            session.invalidate(); // 세션 삭제
        }

        LogoutUserResponse responseDto = new LogoutUserResponse("success");

        return ResponseEntity.ok(responseDto);
    }
}
