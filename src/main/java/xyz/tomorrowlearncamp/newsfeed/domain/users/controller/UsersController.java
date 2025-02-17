package xyz.tomorrowlearncamp.newsfeed.domain.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.DeleteUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdatePasswordRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdateUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.UpdateUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.service.UsersService;

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

    @GetMapping("/me")
    public ResponseEntity<ReadUsersResponseDto> getUser() {
        Long id = 1L;
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReadUsersResponseDto>> getMyInfo() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UpdateUsersResponseDto> updateUser(
            @RequestBody UpdateUsersRequestDto dto
            ) {
        Long id = 1L;
        return new ResponseEntity<>(usersService.updateUser(dto, id), HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @RequestBody UpdatePasswordRequestDto dto
    ) {
        Long id = 1L;
        usersService.updateUserPassword(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteUser(
            @RequestBody DeleteUsersRequestDto dto
    ) {
        Long id = 1L;
        usersService.deleteUser(dto, id);
        // 로그아웃
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
