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
public class CartController extends AbstractController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ModelMapper modelMapper;

    @GetMapping
    protected ResponseEntity<Page<Cart>> getAllCarts(Pageable pageable) {
            Page<Cart> carts = cartService.getAllCart(pageable);
            return sendSuccessResponse(carts);

    }

    @GetMapping("/{id}")
    protected ResponseEntity<Cart> getCartById(@PathVariable String id) {
            Cart cart = cartService.getCartById(id);
            return sendSuccessResponse(cart);
    }

    @PostMapping
    protected ResponseEntity<Cart> createCart(@Valid @RequestBody CreateCartDTO createCartDTO) {
            Cart cart = modelMapper.map(createCartDTO, Cart.class);
            Cart cartCreated = cartService.createCart(cart);
            return sendCreatedResponse(cartCreated);
    }

    @PostMapping("/{id}")
    protected ResponseEntity<Cart> addProductToCart(
            @PathVariable String id,
            @Valid @RequestBody CartItemDTO cartItemDTO) {
            CartItem cartItem = modelMapper.map(cartItemDTO, CartItem.class);
            Cart cart = cartService.addCartItem(id, cartItem);
            return sendCreatedResponse(cart);
    }


    @PutMapping("/{id}/{cartItemId}")
    protected ResponseEntity<CartItem> updateCartItem(
            @PathVariable String id,
            @PathVariable String cartItemId,
            @Valid @RequestBody UpdateCartItemDTO updateCartItemDTO
    ) {
            cartService.getCartById(id);
            CartItem item = modelMapper.map(updateCartItemDTO, CartItem.class);
            CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, item);
            return sendUpdatedResponse(updatedCartItem);
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> deleteCartById(@PathVariable String id) {
            cartService.deleteCartByCartId(id);
            return sendNoContentResponse(id);
    }

    @DeleteMapping("/{id}/{cartItemId}")
    protected ResponseEntity<String> deleteCartItemByCartItemId(
            @PathVariable String id,
            @PathVariable String cartItemId
    ) {
            cartService.getCartById(id);
            cartItemService.deleteCartItemByCartItemId(cartItemId);
            return sendNoContentResponse(cartItemId);
    }
}
