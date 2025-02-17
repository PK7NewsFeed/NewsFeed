package xyz.tomorrowlearncamp.newsfeed.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.SQLException;

public class DuplicateEmailException extends ResponseStatusException {
    public DuplicateEmailException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATION_EMAIL.getMessage());
    }
}
