package xyz.tomorrowlearncamp.newsfeed.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATION_EMAIL("중복된 이메일 입니다."),
    NOT_FOUND_USER("없는 사용자 입니다."),
    INVALID_EMAIL_PASSWORD("이메일 혹은 비밀번호가 틀립니다."),
    NOT_LOGIN("로그인 해주세요."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
