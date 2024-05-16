package com.cartservice.app.controller;

import com.cartservice.app.dto.CartItemDTO;
import com.cartservice.app.dto.CreateCartDTO;
import com.cartservice.app.dto.UpdateCartItemDTO;
import com.cartservice.app.exception.CustomException;
import com.cartservice.app.model.Cart;
import com.cartservice.app.model.CartItem;
import com.cartservice.app.service.CartItemService;
import com.cartservice.app.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${carts.path}")
@RequiredArgsConstructor
public class CartController extends AbstractCartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ModelMapper modelMapper;

    @GetMapping
    protected ResponseEntity<Page<Cart>> getAllCarts(Pageable pageable) {
        try {
            Page<Cart> carts = cartService.getAllCart(pageable);
            return sendSuccessResponse(carts);
        } catch (Exception e) {
            throw new CustomException("Error getting all carts", e);
        }
    }

    @GetMapping("/{id}")
    protected ResponseEntity<Cart> getCartById(@PathVariable String id) {
        try {
            Cart cart = cartService.getCartById(id);
            return sendSuccessResponse(cart);
        } catch (Exception e) {
            throw new CustomException("Error getting cart with id " + id, e);
        }
    }

    @PostMapping
    protected ResponseEntity<Cart> createCart(CreateCartDTO createCartDTO) {
        try {
            Cart cart = modelMapper.map(createCartDTO, Cart.class);
            Cart cartCreated = cartService.createCart(cart);
            return sendCreatedResponse(cartCreated);
        } catch (Exception e) {
            throw new CustomException("Error occurred when creating new cart", e);
        }
    }

    @PostMapping("/{id}")
    protected ResponseEntity<Cart> addProductToCart(
            @PathVariable String id,
            @Valid
            @RequestBody CartItemDTO cartItemDTO) {
        try {
            CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);
            Cart cart = cartService.addCartItem(id, cartItem);
            return sendCreatedResponse(cart);
        } catch (Exception e) {
            throw new CustomException("Error occurred when adding new product to cart", e);
        }
    }


    @PutMapping("/{id}/{cartItemId}")
    protected ResponseEntity<CartItem> updateCartItem(
            @PathVariable String id,
            @PathVariable String cartItemId,
            @Valid
            @RequestBody UpdateCartItemDTO updateCartItemDTO
    ) {
        try {
            cartService.getCartById(id);
            CartItem item = modelMapper.map(updateCartItemDTO, CartItem.class);
            CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, item);
            return sendUpdatedResponse(updatedCartItem);
        } catch (Exception e) {
            throw new CustomException("Error occurred when updating a product with id : " + cartItemId, e);
        }
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> deleteCartById(@PathVariable String id) {
        try {
            cartService.deleteCartByCartId(id);
            return sendNoContentResponse(id);
        } catch (Exception e) {
            throw new CustomException("Error occurred when deleting cart with id : " + id, e);
        }
    }

    @DeleteMapping("/{id}/{cartItemId}")
    protected ResponseEntity<String> deleteCartItemByCartItemId(
            @PathVariable String id,
            @PathVariable String cartItemId
    ) {
        try {
            cartService.getCartById(id);
            cartItemService.deleteCartItemByCartItemId(cartItemId);
            return sendNoContentResponse(cartItemId);
        } catch (Exception e) {
            throw new CustomException("Error occurred when deleting cart item with id : " + cartItemId, e);
        }
    }
}
