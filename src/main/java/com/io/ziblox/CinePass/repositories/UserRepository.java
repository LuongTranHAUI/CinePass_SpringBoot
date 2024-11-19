package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByPhone(String phone);
    Optional<User> findByPhone(String phone);
}
