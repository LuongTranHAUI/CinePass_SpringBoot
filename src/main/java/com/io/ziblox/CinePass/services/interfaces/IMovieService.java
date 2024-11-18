package com.io.ziblox.CinePass.services.interfaces;

import com.io.ziblox.CinePass.dtos.MovieDto;
import com.io.ziblox.CinePass.dtos.MovieImageDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.models.Movie;
import com.io.ziblox.CinePass.models.MovieImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IMovieService {
    Movie createMovie(MovieDto movieDto);
    Movie getMovieById(int id) throws DataNotFoundException;
    Page<Movie> getAllMovie(PageRequest pageRequest);
    Movie updateMovie(int discountId, MovieDto movieDto) throws DataNotFoundException;
    void deleteMovie(int discountId);
    boolean existsByTitle(String title);
    MovieImage createMovieImage(Integer movieId, MovieImageDto movieImageDto);
}
