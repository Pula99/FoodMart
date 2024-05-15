package com.cartservice.app.controller;

import com.cartservice.app.dto.CartItemDTO;
import com.cartservice.app.dto.CreateCartDTO;
import com.cartservice.app.dto.UpdateCartItemDTO;
import com.cartservice.app.model.Cart;
import com.cartservice.app.model.CartItem;
import com.cartservice.app.service.CartItemService;
import com.cartservice.app.service.CartService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${carts.path}")
public class CartController extends AbstractCartController {

    public CartController(CartService cartService, CartItemService cartItemService, ModelMapper modelMapper) {
        super(cartService, cartItemService, modelMapper);
    }

    @GetMapping
        public ResponseEntity<Page<Cart>> getAllCart(Pageable pageable) {
        return getAllCartsResponse(pageable);
    }

    @GetMapping("/{id}")
        public ResponseEntity<Cart> getCartById(@PathVariable String id){
        return getCartByIdResponse(id);
    }

    @PostMapping
        public ResponseEntity<Cart> createCart(@Valid @RequestBody CreateCartDTO createCartDTO){
        return createCartResponse(createCartDTO);
    }

    @PostMapping("/{id}")
        public ResponseEntity<Cart> addProductToCart(
                @PathVariable String id,
                @Valid @RequestBody CartItemDTO cartItemDTO
    ) {
        return addProductToCartResponse(id, cartItemDTO);
    }

    @PutMapping("/{id}/{cartItemId}")
        public ResponseEntity<CartItem> updateCartItem(
                @PathVariable String id,
                @PathVariable String cartItemId,
                @Valid @RequestBody UpdateCartItemDTO updateCartItemDTO
    ){
        return updateCartItemResponse(id, cartItemId,updateCartItemDTO);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCartById(@PathVariable String id) {
        return deleteCartByIdResponse(id);
    }

    @DeleteMapping("/{id}/{cartItemId}")
        public ResponseEntity<Void> deleteCartItemByCartItemId(
                @PathVariable String id,
                @PathVariable String cartItemId
    ) {
        return deleteCartItemByCartItemIdResponse(id, cartItemId);
    }
}
