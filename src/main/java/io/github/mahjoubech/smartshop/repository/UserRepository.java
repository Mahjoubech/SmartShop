package io.github.mahjoubech.smartshop.repository;

import io.github.mahjoubech.smartshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
