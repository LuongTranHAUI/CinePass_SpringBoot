package com.io.ziblox.CinePass.services.interfaces;

import com.io.ziblox.CinePass.dtos.UserDto;
import com.io.ziblox.CinePass.models.User;

public interface IUserService {
    User createUser(UserDto userDto);
    String login(String phone, String password);
}
