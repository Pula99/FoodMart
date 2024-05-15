package com.cartservice.app.service;

import com.cartservice.app.model.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {

    CartItem addCartItem (CartItem cartItem);
    CartItem updateCartItem (String id, CartItem cartItem);
    void deleteCartItemByCartItemId(String id);
}
