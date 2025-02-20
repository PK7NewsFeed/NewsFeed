package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundNewsFeedException extends ResponseStatusException {

    public NotFoundNewsFeedException() { super(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND_NEWSFEED.getMessage());}


}
