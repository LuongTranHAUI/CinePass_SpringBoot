package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Optional<Discount> findByCode(String code);
    boolean existsByCode(String code);
}
