package com.io.ziblox.CinePass.mappers;

import com.io.ziblox.CinePass.entities.Movie;
import com.io.ziblox.CinePass.models.dtos.MovieDto;
import com.io.ziblox.CinePass.models.responses.MovieResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MovieImageMapper.class})
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    Movie toEntity(MovieDto movieDto);

    MovieDto toMovieDto(Movie movie);

    MovieResponse toResponse(Movie movie);

    List<MovieResponse> toResponseList(List<Movie> entityList);

    default Page<MovieResponse> toResponsePage(Page<Movie> moviePage) {
        return moviePage.map(this::toResponse);
    }

    void updateEntityFromDto(MovieDto movieDto, @MappingTarget Movie movie);
}


