package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
