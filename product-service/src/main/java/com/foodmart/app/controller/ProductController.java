package com.foodmart.app.controller;

import com.foodmart.app.dto.CreateProductDTO;
import com.foodmart.app.dto.PatchProductDTO;
import com.foodmart.app.model.Product;
import com.foodmart.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@AllArgsConstructor
@RestController
@RequestMapping("${endpoint.products}")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
        try {
            Page<Product> products = productService.getAllProduct(pageable);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody CreateProductDTO createProductDTO){

        try {
            Product data = modelMapper.map(createProductDTO,Product.class);
            Product newProduct = productService.addProduct(data);
            return ResponseEntity.created(URI.create("" + newProduct.getId())).body(newProduct);
            //return  ResponseEntity.ok(newProduct);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody CreateProductDTO createProductDTO
    ){
        try {
            Product data = modelMapper.map(createProductDTO,Product.class);
            Product newProduct = productService.updateProduct(id, data);
            return  ResponseEntity.ok(newProduct);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Integer id,
            @Valid @RequestBody PatchProductDTO patchProductDTO
    ){
        try {
            Product data = modelMapper.map(patchProductDTO,Product.class);
            Product newProduct = productService.patchProduct(id, data);
            return  ResponseEntity.ok(newProduct);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id
    ){
        try {
            productService.deleteProduct(id);
            return  ResponseEntity.noContent().build();
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
