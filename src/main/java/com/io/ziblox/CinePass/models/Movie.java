package com.io.ziblox.CinePass.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String actor;

    @Column
    private String director;

    @Column
    private String genre;

    @Column  // For the main/thumbnail image URL/filename
    private String thumbnail;

    @Column
    private double rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "run_time")
    private short runTime;

    @Lob
    @Column
    private String summary; // Using @Lob to handle potentially large text

    @Column(nullable = false)
    private String title;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MovieImage> movieImages;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<MovieReview> movieReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Showtime> showtimes;
}
