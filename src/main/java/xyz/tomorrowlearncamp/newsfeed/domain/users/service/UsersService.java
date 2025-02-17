package xyz.tomorrowlearncamp.newsfeed.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.DeleteUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdatePasswordRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.RequestDto.UpdateUsersRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.ReadUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.dto.ResponseDto.UpdateUsersResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.config.PasswordEncoder;
import xyz.tomorrowlearncamp.newsfeed.global.exception.InvalidPasswordException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.MismatchPasswordException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;
    public ReadUsersResponseDto getUserById(Long userId) {
        Users users = usersRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        return new ReadUsersResponseDto(
                users.getId(),
                users.getUsername(),
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
                        users.getUsername(),
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
        Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);

        // 비밀번호 검증
        if (!passwordEncoder.matches(users.getPassword(), dto.getPassword())) {
//            throw new InvalidPasswordException();
        }

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
                users.getUsername(),
                users.getEmail(),
                users.getGender(),
                users.getBirthDate(),
                users.getCreatedAt(),
                users.getUpdatedAt()
        );
    }

    @Transactional
    public void updateUserPassword(UpdatePasswordRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);

        // 비밀번호 검증
//        if (!passwordEncoder.matches(users.getPassword(), dto.getOldPassword())) {
//            throw new InvalidPasswordException();
//        }

        // 새 비밀번호와 비밀번호 확인 검증
        if (!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            throw new MismatchPasswordException();
        }
        users.updatePassword(dto.getNewPassword());
    }

    @Transactional
    public void deleteUser(DeleteUsersRequestDto dto, Long id) {
        Users users = usersRepository.findById(id).orElseThrow(NotFoundUserException::new);

//        if (!passwordEncoder.matches(users.getPassword(), dto.getPassword())) {
//            throw new InvalidPasswordException();
//        }

        usersRepository.deleteById(id);
    }
}
