package com.cartservice.app.service.impl;

import com.cartservice.app.model.Cart;
import com.cartservice.app.repository.CartItemRepository;
import com.cartservice.app.repository.CartRepository;
import com.cartservice.app.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    @Transactional
    public Cart createCart(Cart cart) {
        try {
            cartItemRepository.saveAll(cart.getCartItems());
            return cartRepository.save(cart);
        } catch (Exception exception) {
            log.error("Error occuerd when creating cart with cart number {}, error: {}", cart.getCartNumber(), exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Cart getCartById(Integer id) {
        try{
            return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        } catch (Exception exception) {
            log.error("Error ouccerd when getting cart with id {}", id, exception);
            throw exception;
        }

    }
}
