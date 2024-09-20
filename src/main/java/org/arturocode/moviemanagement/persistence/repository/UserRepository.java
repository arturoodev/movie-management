package org.arturocode.moviemanagement.persistence.repository;

import org.arturocode.moviemanagement.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    Optional<User> findByUsername(String username);

    List<User> findByUsernameContaining(String username);

    @Modifying
    int deleteByUsername(String username);
}
