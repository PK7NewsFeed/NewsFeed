package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExControllerAdvice {

    // 이메일 중복 처리 핸들러
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorMessage> duplicateEmailExHandle(DuplicateEmailException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.DUPLICATION_EMAIL, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorMessage> notFoundUserExHandle(NotFoundUserException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.NOT_FOUND_USER, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(InvalidPasswordOrEmailException.class)
    public ResponseEntity<ErrorMessage> invalidPasswordOrEmailExHandle(InvalidPasswordOrEmailException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.INVALID_EMAIL_PASSWORD, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(LoginUserException.class)
    public ResponseEntity<ErrorMessage> loginUserExHandle(LoginUserException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.NOT_LOGIN, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorMessage> invalidPasswordExHandleExHandle(InvalidPasswordException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.INVALID_PASSWORD, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(MismatchPasswordException.class)
    public ResponseEntity<ErrorMessage> mismatchPasswordException(MismatchPasswordException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.MISMATCH_PASSWORD, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(NotFoundNewsFeedException.class)
    public ResponseEntity<ErrorMessage> notFoundNewsFeedException(NotFoundNewsFeedException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.NOT_FOUND_NEWSFEED, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(SameAsCurrentPasswordException.class)
    public ResponseEntity<ErrorMessage> sameAsCurrentPasswordException(SameAsCurrentPasswordException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.SAME_AS_CURRENT_PASSWORD, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(UnauthorizedWriterException.class)
    public ResponseEntity<ErrorMessage> unauthorizedWriter(UnauthorizedWriterException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.UNAUTHORIZED_WRITER, e.getStatusCode()), e.getStatusCode());
    }

    @ExceptionHandler(SelfLikeNotAllowedException.class)
    public ResponseEntity<ErrorMessage> SelfLikeNotAllowedExHandle(SelfLikeNotAllowedException e) {
        return new ResponseEntity<>(new ErrorMessage(ErrorCode.SELF_LIKE_NOT_ALLOWED, e.getStatusCode()), e.getStatusCode());
    }

    // @Validated 에러 출력
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> globalValidatedExHandle(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ( (FieldError) error ).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
