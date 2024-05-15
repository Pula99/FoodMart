package com.cartservice.app.service.impl;

import com.cartservice.app.exception.CustomException;
import com.cartservice.app.exception.NotFound;
import com.cartservice.app.model.CartItem;
import com.cartservice.app.repository.CartItemRepository;
import com.cartservice.app.service.CartItemService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CartItemImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        try {
            return cartItemRepository.save(cartItem);
        } catch (Exception exception) {
            log.error("Error occurred creating cart item , {}",exception.getMessage());
            throw exception;
        }
    }

    @Override
    public void deleteCartItemByCartItemId(String id) {
        try {
            cartItemRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error occurred when deleting cart with id{}, Error{}",id,exception.getMessage());
            throw exception;
        }
    }

    @Override
    public CartItem updateCartItem(String id, CartItem updatedCartItem){
        try {
            CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new NotFound("item not found with id : %s", id));
            modelMapper.map(updatedCartItem, cartItem);
            return cartItemRepository.save(cartItem);
        } catch (NotFound notFound) {
            throw notFound;
        } catch (Exception exception) {
            log.error("Error occurred when updating cart item with id {}, Error {}",id, exception.getMessage());
            throw new CustomException("Error occurred when updating cart with id : " + id , exception);
        }
    }
}
