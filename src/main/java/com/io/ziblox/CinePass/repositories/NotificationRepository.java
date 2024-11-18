package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Movie, Integer> {
}
