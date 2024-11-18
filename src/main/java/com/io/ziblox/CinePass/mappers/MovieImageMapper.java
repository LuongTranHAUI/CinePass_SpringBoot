package com.io.ziblox.CinePass.mappers;

import com.io.ziblox.CinePass.dtos.MovieImageDto;
import com.io.ziblox.CinePass.models.MovieImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MovieImageMapper extends GenericMapper<MovieImageDto, MovieImage> {
    MovieImageMapper INSTANCE = Mappers.getMapper(MovieImageMapper.class);
}
