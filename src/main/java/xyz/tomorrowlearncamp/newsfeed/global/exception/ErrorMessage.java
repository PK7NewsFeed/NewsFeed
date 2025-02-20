package xyz.tomorrowlearncamp.newsfeed.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {

    private final String message;

    private final Integer httpStatusCode;

    public ErrorMessage(ErrorCode errorCode, HttpStatusCode httpStatus) {
        this.message = errorCode.getMessage();
        this.httpStatusCode = httpStatus.value();
    }
}
