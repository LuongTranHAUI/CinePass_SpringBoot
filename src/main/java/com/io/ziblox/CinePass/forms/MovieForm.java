package com.io.ziblox.CinePass.forms;

import com.io.ziblox.CinePass.dtos.MovieDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovieForm {
    private String actor;
    private String director;
    private Integer duration;
    private String genre;
    private Double rating;
    private String releaseDate;
    private String summary;
    private String title;
    private String trailerUrl;
    private List<MultipartFile> thumbnails;

    public MovieDto getMovieDto() {
        return new MovieDto(actor, director, duration, genre, null, rating, LocalDateTime.parse(releaseDate), summary, title, trailerUrl);
    }
}