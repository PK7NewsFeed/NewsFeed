package xyz.tomorrowlearncamp.newsfeed.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATION_EMAIL("중복된 이메일 입니다."),
    NOT_FOUND_USER("없는 사용자 입니다."),
    INVALID_EMAIL_PASSWORD("이메일 혹은 비밀번호가 틀립니다."),
    NOT_LOGIN("로그인 해주세요."),
    INVALID_PASSWORD("비밀번호가 틀립니다."),
    MISMATCH_PASSWORD("새 비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    SAME_AS_CURRENT_PASSWORD("새 비밀번호는 현재 비밀번호와 같을 수 없습니다."),
    NOT_FOUND_NEWSFEED("없는 뉴스피드 입니다."),
    UNAUTHORIZED_WRITER("작성자가 아닙니다."),
    SELF_LIKE_NOT_ALLOWED("자신이 작성한 글에는 좋아요를 할 수 없습니다."),
    NOT_FOUND_COMMENT("없는 댓글 입니다.")
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
