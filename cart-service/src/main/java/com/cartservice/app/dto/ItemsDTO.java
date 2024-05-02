package com.cartservice.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemsDTO {


    private long id;

    @NotBlank(message = "quantity should not be empty")
    private Integer quantity;

    @NotBlank(message = "price should not be empty")
    private Long price;
}
