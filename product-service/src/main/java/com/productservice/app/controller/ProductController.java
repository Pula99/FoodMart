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
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${products.path}")
@RequiredArgsConstructor
public class ProductController extends AbstractProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    protected ResponseEntity<Page<Product>> getAllProducts (Pageable pageable) {
        try {
            Page<Product> products = productService.getAllProducts(pageable);
            return sendSuccessResponse(products);
        } catch (Exception e) {
            throw new CustomException("Error getting all carts",e);
        }
    }

    @GetMapping("/categories/{category}")
    protected ResponseEntity<Page<Product>> getAllProductsByCategory (
            @PathVariable String category,
            Pageable pageable
    ) {
        try {
            Page<Product> products = productService.getallProductsByCategory(category,pageable);
            return sendSuccessResponse(products);
        } catch (Exception e) {
            throw new CustomException("Error occurred when getting all products with category : " + category ,e);
        }
    }

    @GetMapping("/{id}")
    protected ResponseEntity<Product> getProductById (@PathVariable String id){
        try {
            Product product = productService.getProductById(id);
            return sendSuccessResponse(product);
        } catch (Exception e) {
            throw new CustomException("Error occured when getting product with id : " + id, e);
        }
    }

    @PostMapping
    protected ResponseEntity<Product> addProduct (@Valid @RequestBody CreateProductDTO createProductDTO) {
        try {
            Product product = modelMapper.map(createProductDTO,Product.class);
            Product newProduct = productService.addProduct(product);
            return sendCreatedResponse(newProduct);
        } catch (Exception e) {
            throw new CustomException("Error occurred when adding new products",e);
        }
    }

    @PutMapping("/{id}")
    protected ResponseEntity<Product> updateProductById(
            @PathVariable String id,
            @Valid
            @RequestBody CreateProductDTO createProductDTO
    ) {
        try {
            Product product = modelMapper.map(createProductDTO,Product.class);
            Product updatedProduct = productService.updateProduct(id,product);
            return sendUpdatedResponse(updatedProduct);
        } catch (Exception e) {
            throw new CustomException("Error ocurred when updating product with id : " + id ,e);
        }
    }

    @PatchMapping("/{id}")
    protected ResponseEntity<Product> patchProductById (
            @PathVariable String  id,
            @Valid
            @RequestBody PatchProductDTO patchProductDTO
    ) {
        try {
            Product product = modelMapper.map(patchProductDTO,Product.class);
            Product updatedProduct = productService.patchProduct(id,product);
            return sendUpdatedResponse(updatedProduct);
        } catch (Exception e) {
            throw new CustomException("Error occurred when updating product with id : " + id , e);
        }
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<String> deleteProductId (@PathVariable String id){
        try {
            productService.deleteProduct(id);
            return sendNoContentResponse(id);
        } catch (Exception e) {
            throw new CustomException("Error occurred when deleting product with id : " + id , e);
        }
    }
}
