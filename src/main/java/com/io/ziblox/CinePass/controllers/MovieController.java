package com.io.ziblox.CinePass.controllers;

import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.exceptions.InvalidParamException;
import com.io.ziblox.CinePass.models.dtos.MovieDto;
import com.io.ziblox.CinePass.models.responses.MovieResponse;
import com.io.ziblox.CinePass.models.responses.PagedResponse;
import com.io.ziblox.CinePass.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/movies")
@RequiredArgsConstructor

public class MovieController {
    private final MovieService movieService;

    @PostMapping(value = "")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> createMovie(
            @Valid @RequestBody MovieDto movieDto,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        try {
            movieService.createMovie(movieDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create a new movie successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }

    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> uploadImages(
            @PathVariable int id,
            @RequestParam("files") List<MultipartFile> files
    ) {
        try {
            movieService.addImagesToMovie(id, files);
            return ResponseEntity.status(HttpStatus.CREATED).body("Uploaded images successfully.");
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        } catch (InvalidParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getMovies(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        Page<MovieResponse> movieResponsePage = movieService.getAllMovie(PageRequest.of(page - 1, limit));
        PagedResponse<MovieResponse> pagedResponse = new PagedResponse<>(
                movieResponsePage.getContent(),
                movieResponsePage.getTotalPages()
        );
        return ResponseEntity.ok().body(pagedResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(
            @PathVariable("id") int id
    ) {
        try {
            MovieResponse movieResponse = movieService.getMovieById(id);
            return ResponseEntity.ok().body(movieResponse);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> updateMovie(
            @PathVariable("id") int id,
            @Valid @RequestBody MovieDto movieDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        try {
            movieService.updateMovie(id, movieDto);
            return ResponseEntity.ok().body("Updated movie with id " + id);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMovie(
            @PathVariable("id") int id
    ) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/generateFakeData")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> generateFakeData(Authentication authentication) {
        try {
            for (int i = 0; i < 10; i++) {
                MovieDto fakeMovie = movieService.generateFakeMovie();
                movieService.createMovie(fakeMovie);
            }
            return ResponseEntity.ok().body("Generated fake data successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}