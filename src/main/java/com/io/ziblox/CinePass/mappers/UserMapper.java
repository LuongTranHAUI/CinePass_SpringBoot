package com.io.ziblox.CinePass.mappers;

import com.io.ziblox.CinePass.dtos.UserDto;
import com.io.ziblox.CinePass.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends GenericMapper<UserDto, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}