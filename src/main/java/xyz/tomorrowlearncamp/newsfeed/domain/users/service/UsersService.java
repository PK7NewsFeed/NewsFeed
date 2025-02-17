package xyz.tomorrowlearncamp.newsfeed.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    public ReadUsersResponseDto getUserById(Long userId) {
        Users users = usersRepository.findById(userId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Find By Id")
        );

        return new ReadUsersResponseDto(
                users.getId(),
                users.getName(),
                users.getEmail(),
                users.getGender(),
                users.getBirthDate(),
                users.getCreatedAt(),
                users.getUpdatedAt()
        );
    }

    public List<ReadUsersResponseDto> getUsers() {
        return usersRepository.findAll().stream()
                .map(users -> new ReadUsersResponseDto(
                        users.getId(),
                        users.getName(),
                        users.getEmail(),
                        users.getGender(),
                        users.getBirthDate(),
                        users.getCreatedAt(),
                        users.getUpdatedAt()
                )
        ).collect(Collectors.toList());
    }


}
