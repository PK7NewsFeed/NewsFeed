package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.enums;

import org.springframework.data.domain.Sort;

public enum SortOrder {
    CREATED_AT_DESC("createdAt", Sort.Direction.DESC),
    UPDATED_AT_DESC("updatedAt", Sort.Direction.DESC),
    LIKES_DESC("likes", Sort.Direction.DESC);

    private final String field;
    private final Sort.Direction direction;

    SortOrder(String field, Sort.Direction direction) {
        this.field = field;
        this.direction = direction;
    }

    public Sort toSort() {
        return Sort.by(direction, field);
    }
}
