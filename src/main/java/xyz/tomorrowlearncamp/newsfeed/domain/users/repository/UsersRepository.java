package xyz.tomorrowlearncamp.newsfeed.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.tomorrowlearncamp.newsfeed.domain.users.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    Users findByEmail(String email);
}
