package xyz.tomorrowlearncamp.newsfeed.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.service.AuthService;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.*;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signUp(
            @Validated @RequestBody SignUpUserRequestDto requestDto
    ) {
        SignUpUserResponseDto responseDto = authService.signUp(requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getUsername(),
                requestDto.getGender(),
                requestDto.getBirthDate()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/jwt/login")
    public ResponseEntity<Void> jwtLogin(
            @Validated @RequestBody LoginUserRequestDto requestDto
    ) {
        // 유저 정보 확인
        LoginUserResponseDto responseDto = authService.login(requestDto.getEmail(), requestDto.getPassword());
        String token = jwtUtil.generateToken(responseDto.getId(), requestDto.getEmail());

        // 해더 셋팅
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}
