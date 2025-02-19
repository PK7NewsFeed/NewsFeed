package xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "newsfeeds")
@SQLRestriction("deleted = false")
public class NewsFeed extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deleted = false;

    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public void delete() {
        this.deleted = true;
    }

    public NewsFeed(String title, String content, Users user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
