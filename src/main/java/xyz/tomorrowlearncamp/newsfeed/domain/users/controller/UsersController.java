package xyz.tomorrowlearncamp.newsfeed.domain.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.ReadUsersResponseDto;
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
    public ResponseEntity<ReadUsersResponseDto> getUserId() {
        Long id = 1L;
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<ReadUsersResponseDto>> getUsers() {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }
}
