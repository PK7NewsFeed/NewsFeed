package xyz.tomorrowlearncamp.newsfeed.domain.users.service;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.DeleteUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdatePasswordRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdateUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.UpdateUsersResponseDto;
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
                users.getUserName(),
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
                        users.getUserName(),
                        users.getEmail(),
                        users.getGender(),
                        users.getBirthDate(),
                        users.getCreatedAt(),
                        users.getUpdatedAt()
                )
        ).collect(Collectors.toList());
    }


    @Transactional
    public UpdateUsersResponseDto updateUser(UpdateUsersRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Find By Id"));

        if (dto.getUserName() != null && !dto.getUserName().isBlank()) {
            users.updateUserName(dto.getUserName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            users.updateEmail(dto.getEmail());
        }
        if (dto.getGender() != null) {
            users.updateGender(dto.getGender());
        }
        if (dto.getBirthDate() != null) {
            users.updateBirthDate(dto.getBirthDate());
        }
        return new UpdateUsersResponseDto(
                users.getId(),
                users.getUserName(),
                users.getEmail(),
                users.getGender(),
                users.getBirthDate(),
                users.getCreatedAt(),
                users.getUpdatedAt()
        );
    }

    @Transactional
    public void updateUserPassword(UpdatePasswordRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (users.getPassword() != dto.getOldPassword()) {
            new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (dto.getNewPassword() != dto.getNewPasswordCheck()) {
            new ResponseStatusException((HttpStatus.UNAUTHORIZED));
        }
        users.updatePassword(dto.getNewPassword());
    }

    public void deleteUser(DeleteUsersRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (users.getPassword() != dto.getPassword()) {
            new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        usersRepository.deleteById(id);
    }
}
