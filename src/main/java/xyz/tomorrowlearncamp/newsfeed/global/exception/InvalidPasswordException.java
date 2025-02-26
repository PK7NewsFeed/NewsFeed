package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPasswordException extends ResponseStatusException{
    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_PASSWORD.getMessage());
    }
}
