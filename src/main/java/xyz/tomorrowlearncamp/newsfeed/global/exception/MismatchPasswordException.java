package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MismatchPasswordException extends ResponseStatusException {
    public MismatchPasswordException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.MISMATCH_PASSWORD.getMessage());
    }
}
