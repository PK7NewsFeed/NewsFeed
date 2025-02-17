package xyz.tomorrowlearncamp.newsfeed.auth;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.util.ReflectionTestUtils;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.LoginUserRequestDto;
import xyz.tomorrowlearncamp.newsfeed.auth.dto.SignUpUserRequestDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpUserRequestDtoTest {

    @Test
    public void SignUpUserRequestDtoValidationSuccess() {
        SignUpUserRequestDto signUpUserRequestDto = new SignUpUserRequestDto();
        ReflectionTestUtils.setField(signUpUserRequestDto, "email", "test@test.com");
        ReflectionTestUtils.setField(signUpUserRequestDto, "password", "testPassword123!@#");
        ReflectionTestUtils.setField(signUpUserRequestDto, "username", "username");


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignUpUserRequestDto>> violations = validator.validate(signUpUserRequestDto);

        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "plainaddress",
            "@missingusername.com",
            "user@.com.my",
            "user@domain..com",
            "user@domain_com",
            "user@domain.c",
            "user@domain.toolongtldxyz"
    })
    public void SignUpUserRequestDtoValidationFailedForEmail(String email) {
        SignUpUserRequestDto signUpUserRequestDto = new SignUpUserRequestDto();
        ReflectionTestUtils.setField(signUpUserRequestDto, "email", email);
        ReflectionTestUtils.setField(signUpUserRequestDto, "password", "testPassword123!@#");
        ReflectionTestUtils.setField(signUpUserRequestDto, "username", "username");


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignUpUserRequestDto>> violations = validator.validate(signUpUserRequestDto);

        assertThat(violations).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "password",
            "PASSWORD123",
            "pass1234",
            "PassWord",
            "Pass1234",
            "P1!"
    })
    public void SignUpUserRequestDtoValidationFailedForPassword(String password) {
        SignUpUserRequestDto signUpUserRequestDto = new SignUpUserRequestDto();
        ReflectionTestUtils.setField(signUpUserRequestDto, "email", "test@test.com");
        ReflectionTestUtils.setField(signUpUserRequestDto, "password", password);
        ReflectionTestUtils.setField(signUpUserRequestDto, "username", "username");


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignUpUserRequestDto>> violations = validator.validate(signUpUserRequestDto);

        assertThat(violations).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "resultResultResultResultResult"
    })
    public void SignUpUserRequestDtoValidationFailedForUsername(String username) {
        SignUpUserRequestDto signUpUserRequestDto = new SignUpUserRequestDto();
        ReflectionTestUtils.setField(signUpUserRequestDto, "email", "test@test.com");
        ReflectionTestUtils.setField(signUpUserRequestDto, "password", "testPassword123!@#");
        ReflectionTestUtils.setField(signUpUserRequestDto, "username", username);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignUpUserRequestDto>> violations = validator.validate(signUpUserRequestDto);

        assertThat(violations).isNotEmpty();
    }
}
