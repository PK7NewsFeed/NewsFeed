package xyz.tomorrowlearncamp.newsfeed.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.tomorrowlearncamp.newsfeed.auth.service.temp.UserEntity;
import xyz.tomorrowlearncamp.newsfeed.auth.service.temp.UserRepository;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.users.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.config.PasswordEncoder;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.exception.DuplicateEmailException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.InvalidPasswordOrEmailException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpUserResponseDto signUp(String email, String password, String username, Gender gender, Timestamp birthDate) {

        if (usersRepository.existsByEmail(email)) { // 중복되는 이메일이 있는가
            throw new DuplicateEmailException();
        }

        // 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 빌더
        Users users = Users.builder()
                .email(email)
                .password(encodedPassword)
                .username(username)
                .gender(gender)
                .birthDate(birthDate)
                .build();

        Users savedUser = usersRepository.save(users);

        return SignUpUserResponseDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .createdAt(savedUser.getCreatedAt().toLocalDateTime())
                .build();
    }

    @Override
    public LoginUserResponseDto login(String email, String password) {

        if (!usersRepository.existsByEmail(email)) { // 없는 사용자
            throw new NotFoundUserException();
        }

        Users findUser = usersRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, findUser.getPassword())) { // 비밀번호가 다른 경우
            throw new InvalidPasswordOrEmailException();
        }

        return LoginUserResponseDto.builder()
                .id(findUser.getId())
                .username(findUser.getUsername())
                .build();
    }
}
