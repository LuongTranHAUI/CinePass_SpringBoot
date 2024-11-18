package com.io.ziblox.CinePass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.io.ziblox.CinePass.enums.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotNull(message = "Order id is required")
    @JsonProperty("user_id")
    @Min(value = 1, message = "User id must be greater than 0")
    private Long userId;

    @NotBlank(message = "Full name is required")
    @JsonProperty("fullname")
    private String fullName;

    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 characters")
    @JsonProperty("phone_number")

    private String phoneNumber;

    @NotEmpty(message = "Address is required")
    private String address;

    private String note;

    @NotNull(message = "Total price is required")
    @JsonProperty("total_price")
    @Min(value = 0, message = "Total price must be greater than 0")
    private Double totalPrice;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}
