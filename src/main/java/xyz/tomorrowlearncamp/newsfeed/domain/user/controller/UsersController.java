package xyz.tomorrowlearncamp.newsfeed.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request.DeleteUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request.UpdatePasswordRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request.UpdateUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response.UpdateUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.service.UsersService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.JwtProperties;
import xyz.tomorrowlearncamp.newsfeed.global.util.JwtUtil;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final JwtUtil jwtUtil;

    // 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ReadUsersResponseDto> getUserById(
            @PathVariable Long userId
    ) {
        return new ResponseEntity<>(usersService.getUserById(userId), HttpStatus.OK);
    }

    // 내 정보 조회
    @GetMapping("/myInfo")
    public ResponseEntity<ReadUsersResponseDto> getMyInfo(
            @RequestHeader(JwtProperties.HEADER_STRING) String token
            ) {
        Long userId = jwtUtil.extractUserId(token);
        return new ResponseEntity<>(usersService.getUserById(userId) , HttpStatus.OK);
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<ReadUsersResponseDto>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    // 유저 정보 수정 (비밀번호 제외)
    @PatchMapping
    public ResponseEntity<UpdateUsersResponseDto> updateUser(
            @Validated  @RequestBody UpdateUsersRequestDto dto,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
            ) {
        Long userId = jwtUtil.extractUserId(token);
        return new ResponseEntity<>(usersService.updateUser(dto, userId), HttpStatus.OK);
    }

    // 비밀번호 수정
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @Validated @RequestBody UpdatePasswordRequestDto dto,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long userId = jwtUtil.extractUserId(token);
        usersService.updateUserPassword(dto, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            @Validated @RequestBody DeleteUsersRequestDto dto,
            @RequestHeader(JwtProperties.HEADER_STRING) String token
    ) {
        Long userId = jwtUtil.extractUserId(token);
        // 유저 삭제
        usersService.deleteUser(dto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
