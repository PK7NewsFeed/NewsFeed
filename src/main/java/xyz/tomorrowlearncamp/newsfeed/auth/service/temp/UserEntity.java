package xyz.tomorrowlearncamp.newsfeed.auth.service.temp;

import jakarta.persistence.*;
import lombok.*;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 10)
    private String username;

    @Builder
    public UserEntity(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}