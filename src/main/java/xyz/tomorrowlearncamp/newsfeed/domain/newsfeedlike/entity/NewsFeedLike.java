package xyz.tomorrowlearncamp.newsfeed.domain.newsfeedlike.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.domain.newsFeeds.entity.NewsFeed;
import xyz.tomorrowlearncamp.newsfeed.domain.user.entity.Users;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "newsfeedlike")
public class NewsFeedLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newsfeed_id")
    private NewsFeed newsFeed;

    public NewsFeedLike(Users user, NewsFeed newsFeed) {
        this.user = user;
        this.newsFeed = newsFeed;
    }
}
