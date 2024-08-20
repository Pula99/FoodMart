package com.cartservice.app.service;

import com.cartservice.app.model.Cart;
import com.cartservice.app.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart createCart(Cart cart);
    Cart updateCart(String id, Cart cart);
    Cart getCartById(String id);
    Cart getCartByUserId(String userId);
    Page<Cart> getAllCart(Pageable pageable);
    Cart addCartItem(String id, CartItem cartItem);
    void deleteCartByCartId(String id);


}
