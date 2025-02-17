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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;


public class LoginUserRequestDtoTest {

    @Test
    public void LoginUserRequestDtoValidationSuccess() {
        LoginUserRequestDto loginUserRequestDto = new LoginUserRequestDto();
        ReflectionTestUtils.setField(loginUserRequestDto, "email", "test@test.com");
        ReflectionTestUtils.setField(loginUserRequestDto, "password", "testPassword123!@#");


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginUserRequestDto>> violations = validator.validate(loginUserRequestDto);

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
    public void LoginUserRequestDtoValidationFailedForEmail(String email) {
        LoginUserRequestDto loginUserRequestDto = new LoginUserRequestDto();
        ReflectionTestUtils.setField(loginUserRequestDto, "email", email);
        ReflectionTestUtils.setField(loginUserRequestDto, "password", "testPassword123!@#");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginUserRequestDto>> violations = validator.validate(loginUserRequestDto);

        assertThat(violations).isNotEmpty();

        // 출력하는 방법
//        for(ConstraintViolation<LoginUserRequestDto> violation : violations) {
//            System.out.println("violation : " + violation.getMessage());
//        }
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
    public void LoginUserRequestDtoValidationFailedForPassword(String password) {
        LoginUserRequestDto loginUserRequestDto = new LoginUserRequestDto();
        ReflectionTestUtils.setField(loginUserRequestDto, "email", "test@test.com");
        ReflectionTestUtils.setField(loginUserRequestDto, "password", password);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginUserRequestDto>> violations = validator.validate(loginUserRequestDto);

        assertThat(violations).isNotEmpty();
    }

}
