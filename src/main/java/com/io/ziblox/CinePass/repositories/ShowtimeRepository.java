package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Seat, Integer> {
}