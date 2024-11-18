package com.io.ziblox.CinePass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    @NotNull(message = "Order id is required")
    @JsonProperty("order_id")
    @Min(value = 1, message = "Order id must be greater than 0")
    private Long orderId;

    @NotNull(message = "Movie id is required")
    @JsonProperty("ticket_id")
    @Min(value = 1, message = "Ticket id must be greater than 0")
    private Long ticketId;

    @NotNull(message = "Discount id is required")
    @JsonProperty("discount_id")
    @Min(value = 1, message = "Discount id must be greater than 0")
    private Long discountId;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Total price is required")
    @Min(value = 0, message = "Total price must be greater than 0")
    @JsonProperty("total_price")
    private Double totalPrice;
}
