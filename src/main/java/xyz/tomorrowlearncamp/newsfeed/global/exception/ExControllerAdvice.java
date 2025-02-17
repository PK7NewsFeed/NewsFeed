package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    // 이메일 중복 처리 핸들러
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorCode> duplicateEmailExHandle(DuplicateEmailException e) {
        return new ResponseEntity<>(ErrorCode.DUPLICATION_EMAIL, e.getStatusCode());
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorCode> notFoundUserExHandle(NotFoundUserException e) {
        return new ResponseEntity<>(ErrorCode.NOT_FOUND_USER, e.getStatusCode());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorCode> invalidPasswordExHandle(InvalidPasswordException e) {
        return new ResponseEntity<>(ErrorCode.INVALID_EMAIL_PASSWORD, e.getStatusCode());
    }

    @ExceptionHandler(LoginUserException.class)
    public ResponseEntity<ErrorCode> loginUserExHandle(LoginUserException e) {
        return new ResponseEntity<>(ErrorCode.NOT_LOGIN, e.getStatusCode());
    }

}
