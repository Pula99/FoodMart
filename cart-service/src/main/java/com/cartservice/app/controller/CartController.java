package com.cartservice.app.controller;

import com.cartservice.app.dto.CreateCartDTO;
import com.cartservice.app.model.Cart;
import com.cartservice.app.model.CartItem;
import com.cartservice.app.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("${cartEndpoint.carts}")
public class CartController {

    private final CartService cartService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody CreateCartDTO createCartDTO) {
        try {
            Cart cart = modelMapper.map(createCartDTO, Cart.class);
            Cart cartCreated = cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(cartCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping("{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
        try {
            Cart cart = cartService.getCartById(id);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
