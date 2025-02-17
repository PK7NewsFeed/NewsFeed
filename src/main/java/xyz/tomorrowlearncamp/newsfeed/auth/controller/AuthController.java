package xyz.tomorrowlearncamp.newsfeed.auth.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.tomorrowlearncamp.newsfeed.auth.service.AuthService;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.*;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "인증/인가 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    @ApiResponse(responseCode = "201", description = "회원가입 성공")
    @ApiResponse(responseCode = "400", description = "이메일 중복")
    @ApiResponse(responseCode = "400", description = "입력값 에러")
    public ResponseEntity<SignUpUserResponseDto> signUp(
            @Parameter(required = true, description = "유저 회원가입 시 필요한 기본 정보")
            @Validated @RequestBody SignUpUserRequestDto requestDto
    ) {

        SignUpUserResponseDto responseDto = authService.signUp(requestDto.getEmail(), requestDto.getPassword(), requestDto.getUsername(), requestDto.getGender(), requestDto.getBirthDate());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @ApiResponse(responseCode = "400", description = "입력값 에러")
    @ApiResponse(responseCode = "400", description = "이메일 형식 에러")
    @ApiResponse(responseCode = "401", description = "아이디 및 비밀번호 불일치")
    @ApiResponse(responseCode = "404", description = "없는 사용자")
    public ResponseEntity<LoginUserResponseDto> login(
            @Parameter(required = true, description = "로그인 시 필요한 email, password")
            @Validated @RequestBody LoginUserRequestDto requestDto,
            HttpServletRequest httpServletRequest
    ) {
        // 유저 정보 확인
        LoginUserResponseDto responseDto = authService.login(requestDto.getEmail(), requestDto.getPassword());

        // 세션 설정
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(Const.LOGIN_USER, responseDto);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    @ApiResponse(responseCode = "200", description = "로그아웃 성공")
    @ApiResponse(responseCode = "400", description = "로그아웃된 사용자")
    public ResponseEntity<LogoutUserResponseDto> logout(
            HttpServletRequest httpServletRequest
    ) {
        // 기존 세션 확인
        HttpSession session = httpServletRequest.getSession(false);

        if (session != null) { // 세션이 있다면
            session.invalidate(); // 세션 삭제
        }

        LogoutUserResponseDto responseDto = new LogoutUserResponseDto("success");

        return ResponseEntity.ok(responseDto);
    }
}
