package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SelfLikeNotAllowedException extends ResponseStatusException {
    public SelfLikeNotAllowedException() {
        super(HttpStatus.UNAUTHORIZED, ErrorCode.SELF_LIKE_NOT_ALLOWED.getMessage());
    }
}
