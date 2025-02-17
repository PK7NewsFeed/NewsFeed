package xyz.tomorrowlearncamp.newsfeed.auth.service.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.tomorrowlearncamp.newsfeed.auth.service.temp.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}