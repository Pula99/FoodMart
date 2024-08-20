package com.productservice.app.controller;

import com.productservice.app.dto.CreateProductDTO;
import com.productservice.app.dto.PatchProductDTO;
import com.productservice.app.exception.CustomException;
import com.productservice.app.model.Product;
import com.productservice.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${products.path}")
@RequiredArgsConstructor
public class ProductController extends AbstractController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    protected ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
//        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), 4);
        Page<Product> products = productService.getAllProducts(pageable);
        return sendSuccessResponse(products);
    }

    @GetMapping("/categories/{category}")
    protected ResponseEntity<Page<Product>> getAllProductsByCategory(
            @PathVariable String category,
            Pageable pageable
    ) {
            Page<Product> products = productService.getallProductsByCategory(category, pageable);
            return sendSuccessResponse(products);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return sendSuccessResponse(product);

    }

    @PostMapping
    protected ResponseEntity<Product> addProduct( @Valid @RequestBody CreateProductDTO createProductDTO) {
            Product product = modelMapper.map(createProductDTO, Product.class);
            Product newProduct = productService.addProduct(product);
            return sendCreatedResponse(newProduct);
    }

    @PutMapping("/{id}")
    protected ResponseEntity<Product> updateProductById(
            @PathVariable String id,
            @Valid @RequestBody CreateProductDTO createProductDTO
    ) {
            Product product = modelMapper.map(createProductDTO, Product.class);
            Product updatedProduct = productService.updateProduct(id, product);
            return sendUpdatedResponse(updatedProduct);
    }

    @PatchMapping("/{id}")
    protected ResponseEntity<Product> patchProductById(
            @PathVariable String id,
            @Valid @RequestBody PatchProductDTO patchProductDTO
    ) {
            Product product = modelMapper.map(patchProductDTO, Product.class);
            Product updatedProduct = productService.patchProduct(id, product);
            return sendUpdatedResponse(updatedProduct);

    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> deleteProductId(@PathVariable String id) {
            productService.deleteProduct(id);
            return sendNoContentResponse(id);

    }
}
