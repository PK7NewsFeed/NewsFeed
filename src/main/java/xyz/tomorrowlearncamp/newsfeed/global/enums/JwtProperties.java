package xyz.tomorrowlearncamp.newsfeed.global.enums;

public enum JwtProperties {
    HEADER_STRING("Authorization"),
    TOKEN_PREFIX("Bearer "),
    EXPIRATION_TIME(1000 * 60 * 60),
    APP_TITLE("NewFeeds");

    private final Object value;

    JwtProperties(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getStringValue() {
        return (String) value;
    }

    public int getIntValue() {
        return (int) value;
    }
}
