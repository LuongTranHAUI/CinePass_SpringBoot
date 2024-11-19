package com.io.ziblox.CinePass.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovieResponse extends BaseResponse {
    private Integer id;
    private String actor;
    private String director;
    @JsonProperty("run_time")
    private Integer runTime;
    private String genre;
    private Double rating;
    @JsonProperty("release_date")
    private LocalDateTime releaseDate;
    private String summary;
    private String title;
    @JsonProperty("trailer_url")
    private String trailerUrl;
    @JsonProperty("movie_images")
    private List<MovieImageResponse> movieImages = new ArrayList<>();
}
