package xyz.tomorrowlearncamp.newsfeed.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserResponseDto;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.domain.user.repository.UsersRepository;
import xyz.tomorrowlearncamp.newsfeed.global.config.PasswordEncoder;
import xyz.tomorrowlearncamp.newsfeed.domain.user.enums.Gender;
import xyz.tomorrowlearncamp.newsfeed.global.exception.DuplicateEmailException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.InvalidPasswordOrEmailException;
import xyz.tomorrowlearncamp.newsfeed.global.exception.NotFoundUserException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpUserResponseDto signUp(String email, String password, String username, Gender gender, LocalDate birthDate) {
        // 이메일 중복 검사
        if (!usersRepository.existsByEmail(email)) {
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
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    @Override
    public LoginUserResponseDto login(String email, String password) {

        Users findUser = usersRepository.findByEmail(email).orElseThrow(
                NotFoundUserException::new
        );


        if (!passwordEncoder.matches(password, findUser.getPassword())) { // 비밀번호가 다른 경우
            throw new InvalidPasswordOrEmailException();
        }

        return LoginUserResponseDto.builder()
                .id(findUser.getId())
                .username(findUser.getUsername())
                .build();
    }


    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long userId) {
        return usersRepository.existsById(userId);
    }
}
