package com.io.ziblox.CinePass.configs;

import com.io.ziblox.CinePass.mappers.DiscountMapper;
import com.io.ziblox.CinePass.mappers.MovieImageMapper;
import com.io.ziblox.CinePass.mappers.UserMapper;
import com.io.ziblox.CinePass.mappers.MovieMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public DiscountMapper discountMapper() {
        return DiscountMapper.INSTANCE;
    }

    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

    @Bean
    public MovieMapper movieMapper() {
        return MovieMapper.INSTANCE;
    }

    @Bean
    public MovieImageMapper movieImageMapper() {
        return MovieImageMapper.INSTANCE;
    }
}
