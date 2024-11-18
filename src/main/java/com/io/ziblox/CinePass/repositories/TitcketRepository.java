package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitcketRepository extends JpaRepository<Ticket, Integer> {
}
