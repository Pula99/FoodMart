package com.productservice.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateProductDTO {

    @NotBlank(message = "Product name should not be empty")
    private String productName;

    @NotBlank(message = "Product description should not be empty")
    private String productDescription;

    @NotBlank(message = "Product category should not be empty")
    private String category;

    @NotNull(message = "Product price should not be empty")
    private Long price;
}
