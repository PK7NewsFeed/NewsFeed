package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundCommentException extends ResponseStatusException {
    public NotFoundCommentException() {
        super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_COMMENT.getMessage());
    }
}
