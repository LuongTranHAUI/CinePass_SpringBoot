package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);
    @NonNull
    Page<Movie> findAll(@NonNull Pageable pageable);
    boolean existsByTitle(String title);
}
