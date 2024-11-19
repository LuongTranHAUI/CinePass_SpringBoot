package com.io.ziblox.CinePass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    @NotBlank(message = "Actor is required")
    private String actor;

    @NotBlank(message = "Director's name is required")
    private String director;

    @NotNull(message = "Runtime is required")
    private Integer runTime;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Rating is required")
    private Double rating;

    @NotNull(message = "Release date is required")
    @JsonProperty("release_date")
    private LocalDateTime releaseDate;

    @NotEmpty(message = "Summary is required")
    private String summary;

    @NotBlank(message = "Title is required")
    private String title;

    @JsonProperty("trailer_url")
    private String trailerUrl;
}
