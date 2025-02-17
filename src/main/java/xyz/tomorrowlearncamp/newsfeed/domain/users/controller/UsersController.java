package xyz.tomorrowlearncamp.newsfeed.domain.users.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.DeleteUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdatePasswordRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdateUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.UpdateUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.service.UsersService;
import xyz.tomorrowlearncamp.newsfeed.global.etc.Const;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{userId}")
    public ResponseEntity<ReadUsersResponseDto> getUserById(
            @PathVariable Long userId
    ) {
        return new ResponseEntity<>(usersService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<ReadUsersResponseDto> getMyInfo(
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
            ) {
        return new ResponseEntity<>(usersService.getUserById(loginUser.getId()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReadUsersResponseDto>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UpdateUsersResponseDto> updateUser(
            @RequestBody UpdateUsersRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
            ) {
        return new ResponseEntity<>(usersService.updateUser(dto, loginUser.getId()), HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @RequestBody UpdatePasswordRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser
    ) {
        usersService.updateUserPassword(dto, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteUser(
            @RequestBody DeleteUsersRequestDto dto,
            @SessionAttribute(name = Const.LOGIN_USER) LoginUserResponseDto loginUser,
            HttpServletRequest httpServletRequest
    ) {
        // 유저 삭제
        usersService.deleteUser(dto, loginUser.getId());

        // 세션 삭제
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
