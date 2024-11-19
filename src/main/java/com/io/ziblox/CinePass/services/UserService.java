package com.io.ziblox.CinePass.services;

import com.io.ziblox.CinePass.models.dtos.UserDto;
import com.io.ziblox.CinePass.mappers.UserMapper;
import com.io.ziblox.CinePass.entities.User;
import com.io.ziblox.CinePass.repositories.UserRepository;
import com.io.ziblox.CinePass.services.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User createUser(UserDto userDto) {
        String phoneNumber = userDto.getPhone();
        if (userRepository.existsByPhone(phoneNumber)) {
            throw new RuntimeException("Phone number already exists");
        }
        User newUser = userMapper.toEntity(userDto);
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public String login(String phone, String password) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return "";
    }
}