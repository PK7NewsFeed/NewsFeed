package xyz.tomorrowlearncamp.newsfeed.global.etc;

public interface ErrorMessage {
    String EMPTY_CONTENT = "내용이 비어있습니다.";
    String INVALID_EMAIL_FORMAT = "이메일 형식이 아닙니다.";
    String INVALID_PASSWORD_FORMAT = "비밀번호 형식이 올바르지 않습니다.";
    String INVALID_PASSWORD_LENGTH = "비밀번호 길이가 올바르지 않습니다. (8~20자)";
    String INVALID_USERNAME_LENGTH = "사용자 이름 길이가 올바르지 않습니다. (1~10자)";
    String INVALID_EMAIL_LENGTH = "이메일 길이가 올바르지 않습니다. (8~30자)";
    String MISSING_GENDER = "성별을 선택해주세요.";
    String MISSING_BIRTHDATE = "태어난 날짜를 선택해주세요.";
    String EMPTY_COMMENT = "댓글 내용을 입력해주세요.";
    String EMPTY_USER = "사용자 필수 입력값입니다.";
    String EMPTY_POST = "게시글 필수 입력값입니다.";
}