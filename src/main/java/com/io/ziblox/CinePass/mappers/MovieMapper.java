package com.io.ziblox.CinePass.mappers;

import com.io.ziblox.CinePass.dtos.MovieDto;
import com.io.ziblox.CinePass.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MovieMapper extends GenericMapper<MovieDto, Movie> {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
}
