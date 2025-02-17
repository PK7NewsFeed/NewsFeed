package xyz.tomorrowlearncamp.newsfeed.domain.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.tomorrowlearncamp.newsfeed.global.entity.BaseEntity;
import xyz.tomorrowlearncamp.newsfeed.global.enums.Gender;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Timestamp birthDate;

    public void updateUserName(String userName) {
        this.userName = userName;
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
}
