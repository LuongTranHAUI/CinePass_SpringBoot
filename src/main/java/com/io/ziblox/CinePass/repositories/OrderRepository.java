package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
