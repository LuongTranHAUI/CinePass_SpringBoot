package com.io.ziblox.CinePass.services.interfaces;

import com.io.ziblox.CinePass.models.dtos.MovieDto;
import com.io.ziblox.CinePass.models.dtos.MovieImageDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.entities.MovieImage;
import com.io.ziblox.CinePass.models.responses.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IMovieService {
    void createMovie(MovieDto movieDto);
    MovieResponse getMovieById(int id) throws DataNotFoundException;
    Page<MovieResponse> getAllMovie(PageRequest pageRequest);
    void updateMovie(int discountId, MovieDto movieDto) throws DataNotFoundException;
    void deleteMovie(int discountId);
    boolean existsByTitle(String title);
    MovieImage createMovieImage(Integer movieId, MovieImageDto movieImageDto);
}
