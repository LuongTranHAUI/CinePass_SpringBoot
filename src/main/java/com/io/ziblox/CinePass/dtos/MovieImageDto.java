package com.io.ziblox.CinePass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieImageDto {
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("movie_id")
    private Integer movieId;
}
