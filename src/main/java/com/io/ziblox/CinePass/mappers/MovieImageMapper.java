package com.io.ziblox.CinePass.mappers;

import com.io.ziblox.CinePass.models.dtos.MovieImageDto;
import com.io.ziblox.CinePass.entities.MovieImage;
import com.io.ziblox.CinePass.models.responses.MovieImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieImageMapper {
    MovieImageMapper INSTANCE = Mappers.getMapper(MovieImageMapper.class);

    MovieImage toEntity(MovieImageDto movieImageDto);

    MovieImageDto toMovieImageDto(MovieImage movieImage);

    @Mapping(source = "movie.id", target = "movieId")
    MovieImageResponse toResponse(MovieImage movieImage);

    List<MovieImageResponse> toResponseList(List<MovieImage> entityList);
}
