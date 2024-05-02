package com.cartservice.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateCartDTO {

    public List<ItemsDTO> cartItems;
}
