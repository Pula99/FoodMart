package com.cartservice.app.service;

import com.cartservice.app.model.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart createCart(Cart cart) throws Exception;

    Cart getCartById(Integer id) throws Exception;
}
