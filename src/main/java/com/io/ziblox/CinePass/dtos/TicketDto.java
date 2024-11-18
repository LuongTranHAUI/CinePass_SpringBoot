package com.io.ziblox.CinePass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.io.ziblox.CinePass.enums.TicketType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    @Min(value = 1, message = "Discount must be greater than 0")
    @Max(value = 100, message = "Discount must be less than 100")
    private Double discount;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    @Max(value = 10000000, message = "Price must be less than 10,000,000")
    private Double price;

    @NotBlank(message = "Seat number is required")
    @JsonProperty("seat_number")
    private String seatNumber;

    @NotNull(message = "Type is required")
   private TicketType type;

    @NotNull(message = "Show time id is required")
    @JsonProperty("show_time_id")
    @Min(value = 1, message = "Show time id must be greater than 0")
    private Long showTimeId;

    @NotNull(message = "User id is required")
    @JsonProperty("user_id")
    @Min(value = 1, message = "User id must be greater than 0")
    private Long userId;

    @NotNull(message = "Payment id is required")
    @JsonProperty("payment_id")
    @Min(value = 1, message = "Payment id must be greater than 0")
    private Long paymentId;
}
