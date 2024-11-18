package com.io.ziblox.CinePass.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDto {
    @NotBlank(message = "Code is required")
    private String code;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Discount percent is required")
    @Min(value = 0, message = "Discount percent must be greater than 0")
    @Max(value = 100, message = "Discount percent must be less than 100")
    private Double discountPercent;

    @NotNull(message = "Expiration date is required")
    private LocalDate expirationDate;
}
