package com.cartservice.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CartItemDTO {


    private Integer productId;

    @NotBlank(message = "quantity should not be empty")
    private Integer quantity;
}
