package xyz.tomorrowlearncamp.newsfeed.domain.comment.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.domain.newsfeed.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private NewsFeed newsFeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    @Nullable
    private Comment parentComment;

    @Column(nullable = false)
    private Integer depth;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deleted = false;

    @Builder
    public Comment(Users user, NewsFeed newsFeed, @Nullable Comment parentComment, String content, Integer depth) {
        this.user = user;
        this.newsFeed = newsFeed;
        this.parentComment = parentComment;
        this.content = content;
        this.depth = depth;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    public void delete() {
        this.deleted = true;
    }
}
