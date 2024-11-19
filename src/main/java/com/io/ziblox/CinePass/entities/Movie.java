package com.io.ziblox.CinePass.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @Column(nullable = false)
    private String title;

    @Lob
    @Column
    private String description;

    private double rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "run_time")
    private Integer runTime;

    @Column
    private String genre;
    @Column
    private String actor;
    @Column
    private String director;
    @Column(name = "trailer_url")
    private String trailerUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieImage> movieImages;
}
