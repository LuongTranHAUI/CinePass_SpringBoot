package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.entities.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieImageRepository extends JpaRepository<MovieImage, Integer> {
    Integer countByMovieId(Integer movieId);
}
