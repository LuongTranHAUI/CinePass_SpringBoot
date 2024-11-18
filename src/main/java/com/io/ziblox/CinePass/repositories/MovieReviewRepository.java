package com.io.ziblox.CinePass.repositories;

import com.io.ziblox.CinePass.models.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer> {
}
