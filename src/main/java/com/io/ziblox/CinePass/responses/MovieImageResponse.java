package com.io.ziblox.CinePass.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieImageResponse {
    private Integer id;
    private String imageUrl;
}
