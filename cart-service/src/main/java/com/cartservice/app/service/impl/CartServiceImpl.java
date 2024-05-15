package com.cartservice.app.service.impl;

import com.cartservice.app.exception.CustomException;
import com.cartservice.app.exception.NotFound;
import com.cartservice.app.model.Cart;
import com.cartservice.app.model.CartItem;
import com.cartservice.app.repository.CartItemRepository;
import com.cartservice.app.repository.CartRepository;
import com.cartservice.app.service.CartItemService;
import com.cartservice.app.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    private final CartItemService cartItemService;


    @Override
    public Page<Cart> getAllCart(Pageable pageable) {
        try {
           return cartRepository.findAll(pageable);
        } catch (Exception exception) {
            log.error("Error occurred when getting all carts");
            throw exception;
        }
    }


    @Override
    @Transactional
    public Cart createCart(Cart cart) {
        try {
            cart.setCartItems(new ArrayList<>());
            return cartRepository.save(cart);
        } catch (Exception exception) {
            log.error("Error occurred when creating cart with cart number {}, error: {}", cart.getId(), exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Cart getCartById(String id) {
        try {
            return cartRepository.findById(id).orElseThrow(() -> new NotFound("Cart not found with id : %s", id));
        } catch (NotFound notFound) {
            throw notFound;
        } catch (Exception exception) {
            log.error("Error occurred when getting cart with id {}", id, exception);
            throw new CustomException("Error occurred when getting cart with id : " + id, exception);
        }

    }

    @Override
    public Cart addCartItem(String id, CartItem cartItem) {
        Cart cart = getCartById(id);
        cartItem.setCart(cart);
        CartItem cartItem1 = cartItemService.addCartItem(cartItem);
        cart.getCartItems().add(cartItem1);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCartByCartId(String id) {
        try {
            cartRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error occurred when deleting cart with id{}, Error{}",id,exception.getMessage());
            throw exception;
        }
    }


}
