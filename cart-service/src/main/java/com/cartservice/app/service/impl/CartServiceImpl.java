package com.cartservice.app.service.impl;

import com.cartservice.app.dto.CartItemProductDTO;
import com.cartservice.app.model.Cart;
import com.cartservice.app.repository.CartItemRepository;
import com.cartservice.app.repository.CartRepository;
import com.cartservice.app.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public Cart createCart(Cart cart) {
        try {
            cartItemRepository.saveAll(cart.getCartItems());
            return cartRepository.save(cart);
        } catch (Exception exception) {
            log.error("Error occuerd when creating cart with cart number {}, error: {}", cart.getId(), exception.getMessage());
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

    public CartItemProductDTO getProductById(Integer productId){
        String productApiUrl = "http://localhost:8080/products/" + productId;
        return restTemplate.getForObject(productApiUrl, CartItemProductDTO.class);
    }
}
