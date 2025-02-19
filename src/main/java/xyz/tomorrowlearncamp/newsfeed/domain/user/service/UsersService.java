package xyz.tomorrowlearncamp.newsfeed.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request.DeleteUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request.UpdatePasswordRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.request.UpdateUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.dto.response.UpdateUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.config.PasswordEncoder;
import xyz.tomorrowlearncamp.newsfeed.global.exception.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public ReadUsersResponseDto getUserById(Long userId) {
        Users users = usersRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        return ReadUsersResponseDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .username(users.getUsername())
                .gender(users.getGender())
                .birthDate(users.getBirthDate())
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt())
                .build();
    }

    public Users getUserEntityById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }

    public List<ReadUsersResponseDto> getUsers() {
        return usersRepository.findAll().stream()
                .map(users -> ReadUsersResponseDto.builder()
                        .id(users.getId())
                        .email(users.getEmail())
                        .username(users.getUsername())
                        .gender(users.getGender())
                        .birthDate(users.getBirthDate())
                        .createdAt(users.getCreatedAt())
                        .updatedAt(users.getUpdatedAt())
                        .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public UpdateUsersResponseDto updateUser(UpdateUsersRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.getPassword(), users.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            users.updateUserName(dto.getUsername());
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

        return UpdateUsersResponseDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .username(users.getUsername())
                .gender(users.getGender())
                .birthDate(users.getBirthDate())
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt())
                .build();
    }

    @Transactional
    public void updateUserPassword(UpdatePasswordRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.getOldPassword(), users.getPassword())) {
            throw new InvalidPasswordException();
        }

        // 새 비밀번호와 비밀번호 확인 검증
        if (!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            throw new MismatchPasswordException();
        }
        // 현재 비밀번호와 새 비밀번호가 동일한 경우
        if (dto.getOldPassword().equals(dto.getNewPassword())){
            throw new SameAsCurrentPasswordException();
        }
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getNewPassword());
        users.updatePassword(encodedPassword);
    }

    @Transactional
    public void deleteUser(DeleteUsersRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);

        // 비밀번호 검증
        if (!passwordEncoder.matches(dto.getPassword(), users.getPassword())) {
            throw new InvalidPasswordException();
        }

        users.delete();
    }

    public void validateUserExists(Long userId) {
        if (!usersRepository.existsById(userId)) {
            throw new NotFoundUserException();
        }
    }
}
