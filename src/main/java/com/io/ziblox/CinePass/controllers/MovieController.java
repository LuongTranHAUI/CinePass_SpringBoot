package com.io.ziblox.CinePass.controllers;

import com.io.ziblox.CinePass.models.dtos.MovieDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.exceptions.InvalidParamException;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/movies") //Đánh version cho API giúp nâng cấp dễ dàng hơn
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    //Thêm một phim mới
    @PostMapping(value = "")
    public ResponseEntity<?> createMovie(
            @Valid
            @RequestBody MovieDto movieDto,
            BindingResult bindingResult
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList());
            }
            movieService.createMovie(movieDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Create a new movie successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }

    //Thêm ảnh cho phim mới
    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @PathVariable int id,
            @RequestParam("files") List<MultipartFile> files
    ) {
        try {
            movieService.addImagesToMovie(id, files);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        } catch (InvalidParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Uploaded images successfully");
    }

    //Hiển thị danh sách các phim
    @GetMapping("") //http://localhost:8088/api/v1/movies?page=1&limit=10
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

    //Hiển thị thông tin chi tiết một phim theo id
    @GetMapping("/{id}") //http://localhost:8088/api/v1/movies/1
    public ResponseEntity<?> getMovieById(
            @PathVariable("id") int id
    ) throws DataNotFoundException {
        try {
            MovieResponse movieResponse = movieService.getMovieById(id);
            return ResponseEntity.ok().body(movieResponse);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Sửa thông tin một phim
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(
            @PathVariable("id") int id,
            @Valid
            @RequestBody MovieDto movieDto,
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

    //Xóa một phim
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(
            @Valid
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
    public ResponseEntity<?> generateFakeData() {
        try {
            for (int i = 0; i < 10; i++){
                MovieDto fakeMovie = movieService.generateFakeMovie();
                movieService.createMovie(fakeMovie);
            }
            return ResponseEntity.ok().body("Generated fake data successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
