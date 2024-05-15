package com.productservice.app.controller;

import com.productservice.app.dto.CreateProductDTO;
import com.productservice.app.dto.PatchProductDTO;
import com.productservice.app.model.Product;
import com.productservice.app.service.ProductService;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${products.path}")
public class ProductController extends AbstractProductController {

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        super(productService, modelMapper);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts (Pageable pageable) {
        return getAllProductsResponse(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable String  id) {
        return getProductByIdResponse(id);
    }

    @GetMapping("categories/{category}")
    public ResponseEntity<Page<Product>> getAllProductsByCategory (
            @PathVariable String category,
            Pageable pageable
    ) {
        return getAllProductsByCategoryResponse(category , pageable);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct (CreateProductDTO createProductDTO) {
        return addProductResponse(createProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById (
            @PathVariable String id,
            CreateProductDTO createProductDTO
    ) {
        return updateProductByIdResponse(id, createProductDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProductById (
            @PathVariable String id,
            PatchProductDTO patchProductDTO
    ) {
        return patchProductByIdResponse(id,patchProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById (@PathVariable String id) {
        return deleteProductByIdResponse(id);
    }
}
