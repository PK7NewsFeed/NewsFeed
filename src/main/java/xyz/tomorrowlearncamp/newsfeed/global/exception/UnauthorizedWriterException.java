package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedWriterException extends ResponseStatusException {
    public UnauthorizedWriterException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.UNAUTHORIZED_WRITER.getMessage());
    }
}
