package com.io.ziblox.CinePass.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovieImageResponse extends BaseResponse {
    private Integer id;
    @JsonProperty("movie_id")
    private Integer movieId;
    @JsonProperty("image_url")
    private String imageUrl;
}
