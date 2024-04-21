package com.foodmart.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class PatchProductDTO {

    private String productName;

    private String productDescription;

    private String category;

    private Long price;
}
