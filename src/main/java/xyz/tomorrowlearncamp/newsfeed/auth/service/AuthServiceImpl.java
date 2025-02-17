package xyz.tomorrowlearncamp.newsfeed.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.tomorrowlearncamp.newsfeed.auth.service.temp.UserEntity;
import xyz.tomorrowlearncamp.newsfeed.auth.service.temp.UserRepository;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.global.config.PasswordEncoder;
import xyz.tomorrowlearncamp.newsfeed.global.exception.DuplicateEmailException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.InvalidPasswordException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpUserResponseDto signUp(String email, String password, String username) {

        if (userRepository.existsByEmail(email)) { // 중복되는 이메일이 있는가
            throw new DuplicateEmailException();
        }

        // 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 빌더
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(encodedPassword)
                .username(username)
                .build();

        UserEntity savedUser = userRepository.save(user);

        return SignUpUserResponseDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .createdAt(savedUser.getCreatedAt().toLocalDateTime())
                .build();
    }

    @Override
    public LoginUserResponseDto login(String email, String password) {

        if (!userRepository.existsByEmail(email)) { // 없는 사용자
            throw new NotFoundUserException();
        }

        UserEntity findUser = userRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, findUser.getPassword())) { // 비밀번호가 다른 경우
            throw new InvalidPasswordException();
        }

        return LoginUserResponseDto.builder()
                .id(findUser.getId())
                .username(findUser.getUsername())
                .build();
    }
}
