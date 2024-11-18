package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Integer> {
}
