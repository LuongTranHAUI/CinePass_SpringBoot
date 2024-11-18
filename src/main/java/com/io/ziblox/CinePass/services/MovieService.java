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
    public Movie createMovie(MovieDto movieDto) {
        movieRepository.findByTitle(movieDto.getTitle())
                .ifPresent(movie -> {
                    throw new RuntimeException("Movie already exists");
                });
        Movie newMovie = movieMapper.toEntity(movieDto);
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie getMovieById(int id) throws DataNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
    }

    @Override
    public Page<Movie> getAllMovie(PageRequest pageRequest) {
        return movieRepository.findAll(pageRequest);
    }

    @Override
    public Movie updateMovie(int movieId, MovieDto movieDto) throws DataNotFoundException {
        Movie existingMovie = getMovieById(movieId);
        Movie updatedMovie = movieMapper.toEntity(movieDto);
        updatedMovie.setId(existingMovie.getId());
        updatedMovie.setCreatedAt(existingMovie.getCreatedAt());
        return movieRepository.save(updatedMovie);
    }

    @Override
    public void deleteMovie(int movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public boolean existsByTitle(String title) {
        return movieRepository.existsByTitle(title);
    }

    //Thêm ảnh cho phim
    @Override
    public MovieImage createMovieImage(Integer movieId, MovieImageDto movieImageDto) throws DataNotFoundException {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found"));
        MovieImage movieImage = movieImageMapper.toEntity(movieImageDto);
        movieImage.setMovie(existingMovie); // Set the movie field
        // Không cho phép tạo quá 5 ảnh cho một phim
        if (movieImageRepository.countByMovieId(movieId) >= 5) {
            throw new InvalidParamException("Movie can only have 5 images");
        }
        return movieImageRepository.save(movieImage);
    }

    @Transactional
    public void addImagesToMovie(int movieId, List<MultipartFile> files) throws IOException, DataNotFoundException {
        Movie existingMovie = getMovieById(movieId);
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
}
