package xyz.tomorrowlearncamp.newsfeed.domain.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.CreateCommentRequestDto;
import xyz.tomorrowlearncamp.newsfeed.domain.comment.dto.CreateCommentResponseDto;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

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
    private Comment parentComment;

    @Column
    private Integer depth;

    @Column
    private String content;

    public Comment(Users user, NewsFeed newsFeed, Comment parentComment, String content) {
        this.user = user;
        this.newsFeed = newsFeed;
        this.parentComment = parentComment;
        this.content = content;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}
