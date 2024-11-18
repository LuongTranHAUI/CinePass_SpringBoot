package com.io.ziblox.CinePass.services;

import com.io.ziblox.CinePass.dtos.MovieDto;
import com.io.ziblox.CinePass.dtos.MovieImageDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.exceptions.InvalidParamException;
import com.io.ziblox.CinePass.mappers.MovieImageMapper;
import com.io.ziblox.CinePass.mappers.MovieMapper;
import com.io.ziblox.CinePass.models.Movie;
import com.io.ziblox.CinePass.models.MovieImage;
import com.io.ziblox.CinePass.repositories.MovieImageRepository;
import com.io.ziblox.CinePass.repositories.MovieRepository;
import com.io.ziblox.CinePass.responses.MovieResponse;
import com.io.ziblox.CinePass.services.interfaces.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;
    private final MovieMapper movieMapper;
    private final MovieImageMapper movieImageMapper;

    @Override
    public void createMovie(MovieDto movieDto) {
        movieRepository.findByTitle(movieDto.getTitle())
                .ifPresent(movie -> {
                    throw new RuntimeException("Movie already exists");
                });
        Movie newMovie = movieMapper.toEntity(movieDto);
        movieRepository.save(newMovie);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieResponse getMovieById(int id) throws DataNotFoundException {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
        return movieMapper.toResponse(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MovieResponse> getAllMovie(PageRequest pageRequest) {
        Page<Movie> moviePage = movieRepository.findAll(pageRequest);
        return movieMapper.toResponsePage(moviePage);
    }

    @Override
    @Transactional
    public void updateMovie(int movieId, MovieDto movieDto) throws DataNotFoundException {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
        Movie updatedMovie = movieMapper.toEntity(movieDto);
        updatedMovie.setId(existingMovie.getId());
        updatedMovie.setCreatedAt(existingMovie.getCreatedAt());
        movieRepository.save(updatedMovie);
    }

    @Override
    @Transactional
    public void deleteMovie(int movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public boolean existsByTitle(String title) {
        return movieRepository.existsByTitle(title);
    }

    @Override
    @Transactional
    public MovieImage createMovieImage(Integer movieId, MovieImageDto movieImageDto) throws DataNotFoundException {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
        MovieImage movieImage = movieImageMapper.toEntity(movieImageDto);
        movieImage.setMovie(existingMovie);
        return movieImageRepository.save(movieImage);
    }

    @Transactional
    public void addImagesToMovie(int movieId, List<MultipartFile> files) throws IOException, DataNotFoundException {
        if (files.size() > 5) {
            throw new InvalidParamException("You can upload a maximum of 5 images at a time");
        }
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));

        int existingImageCount = movieImageRepository.countByMovieId(movieId);
        if (existingImageCount + files.size() > 5) {
            throw new InvalidParamException("A movie can have a maximum of 5 images");
        }

        for (MultipartFile file : files) {
            if (file.getSize() == 0) {
                continue;
            }
            if (file.getSize() > 10 * 1024 * 1024) {
                throw new IOException("File size must be less than 10MB");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IOException("Only image files are supported");
            }
            String filename = storeFile(file);
            MovieImage movieImage = new MovieImage();
            movieImage.setImageUrl(filename);
            movieImage.setMovie(existingMovie);
            existingMovie.getMovieImages().add(movieImage);
            if (existingMovie.getThumbnail() == null) {
                existingMovie.setThumbnail(filename);
            }
        }
        movieRepository.save(existingMovie);
    }

    private String storeFile(MultipartFile file) throws IOException {
        if(!isImage(file)) {
            throw new IOException("Only image files are supported");
        }
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID() + "_" + filename;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
