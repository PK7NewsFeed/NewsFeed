package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    // 이메일 중복 처리 핸들러
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorCode> duplicateEmailExHandle() {
        return new ResponseEntity<>(ErrorCode.DUPLICATION_EMAIL, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorCode> notFoundUserExHandle() {
        return new ResponseEntity<>(ErrorCode.NOT_FOUND_USER, HttpStatus.NOT_FOUND);
    }

}
