package com.io.ziblox.CinePass.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovieResponse extends BaseResponse {
    private Integer id;
    private String title;
    private String description;
    private double rating;
    private LocalDate releaseDate;
    private Integer runTime;
    private String genre;
    private String actor;
    private String director;
    private String trailerUrl;
    private List<MovieImageResponse> movieImages = new ArrayList<>();
}
