package com.io.ziblox.CinePass.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotBlank(message = "Phone number is required")
    @JsonProperty("phone")
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;
}
