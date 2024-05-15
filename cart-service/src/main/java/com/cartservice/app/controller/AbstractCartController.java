package com.cartservice.app.controller;

import com.cartservice.app.dto.CartItemDTO;
import com.cartservice.app.dto.CreateCartDTO;
import com.cartservice.app.dto.UpdateCartItemDTO;
import com.cartservice.app.exception.CustomException;
import com.cartservice.app.model.Cart;
import com.cartservice.app.model.CartItem;
import com.cartservice.app.service.CartItemService;
import com.cartservice.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RequiredArgsConstructor
public abstract class AbstractCartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ModelMapper modelMapper;

    protected ResponseEntity<Page<Cart>> getAllCartsResponse(Pageable pageable) {
        try {
            Page<Cart> carts = cartService.getAllCart(pageable);
            return ResponseEntity.ok(carts);
        } catch (Exception e) {
            throw new CustomException("Error getting all carts", e);
        }
    }

    protected ResponseEntity<Cart> createCartResponse(CreateCartDTO createCartDTO) {
        try {
            Cart cart = modelMapper.map(createCartDTO, Cart.class);
            Cart cartCreated = cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(cartCreated);
        } catch (Exception e) {
            throw new CustomException("Error occurred when creating new cart",e);
        }
    }

    protected ResponseEntity<Cart> getCartByIdResponse(String id) {
        try {
            Cart cart = cartService.getCartById(id);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            throw new CustomException("Error getting cart with id " + id, e);
        }
    }

    protected ResponseEntity<Cart> addProductToCartResponse(String cartId, CartItemDTO cartItemDTO) {
        try {
            CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCartItem(cartId, cartItem));
        } catch (Exception e) {
            throw new CustomException("Error occurred when adding new product to cart",e);
        }
    }

    protected ResponseEntity<CartItem> updateCartItemResponse(String id, String cartItemId, UpdateCartItemDTO updateCartItemDTO){
        try {
            cartService.getCartById(id);
            CartItem item = modelMapper.map(updateCartItemDTO,CartItem.class);
            CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, item);
            return ResponseEntity.ok(updatedCartItem);
        } catch (Exception e) {
            throw new CustomException("Error occurred when updating a product with id : " + cartItemId, e);
        }
    }

    protected ResponseEntity<Void> deleteCartByIdResponse(String id){
        try {
            cartService.deleteCartByCartId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new CustomException("Error occurred when deleting cart with id : " + id, e);
        }
    }

    protected ResponseEntity<Void> deleteCartItemByCartItemIdResponse(String id, String cartItemId){
        try {
            cartService.getCartById(id);
            cartItemService.deleteCartItemByCartItemId(cartItemId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new CustomException("Error occurred when deleting cart item with id : " + cartItemId,e);
        }
    }


}
