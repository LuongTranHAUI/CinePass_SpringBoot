package com.io.ziblox.CinePass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String address;

    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Fullname is required")
    @JsonProperty("fullname")
    private String fullName;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone number is required")
    @JsonProperty("phone")
    private String phone;

    @NotBlank(message = "Retype password is required")
    @JsonProperty("retype_password")
    private String retypePassword;

    @NotNull(message = "Date of birth is required")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("facebook_account_id")
    private Long facebookAccountId;

    @JsonProperty("google_account_id")
    private Long googleAccountId;

    @NotNull(message = "Role ID is required")
    @JsonProperty("role_id")
    private Long roleId;
}
