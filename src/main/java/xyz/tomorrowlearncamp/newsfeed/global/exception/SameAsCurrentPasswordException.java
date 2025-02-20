package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SameAsCurrentPasswordException extends ResponseStatusException {

    public SameAsCurrentPasswordException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.SAME_AS_CURRENT_PASSWORD.getMessage());
    }
}
