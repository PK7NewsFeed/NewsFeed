package xyz.tomorrowlearncamp.newsfeed.domain.users.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@SQLRestriction("deleted = false")
@Getter
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Timestamp birthDate;

    @Column(nullable = false)
    private boolean deleted = false;

    public void updateUserName(String username) {
        this.username = username;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateGender(Gender gender) {
        this.gender = gender;
    }

    public void updateBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void delete() {
        this.deleted = true;
    }
    @Builder
    public Users(String email, String password, String username, Gender gender, Timestamp birthDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
    }


}
