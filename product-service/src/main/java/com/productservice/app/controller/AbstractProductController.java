package com.productservice.app.controller;

import com.productservice.app.dto.CreateProductDTO;
import com.productservice.app.dto.PatchProductDTO;
import com.productservice.app.exception.CustomException;
import com.productservice.app.model.Product;
import com.productservice.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public abstract class AbstractProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;


    protected ResponseEntity<Page<Product>> getAllProductsResponse (Pageable pageable){
        try {
            Page<Product> products = productService.getAllProducts(pageable);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            throw new CustomException("Error occurred when getting all products" , e);
        }
    }

    protected ResponseEntity<Page<Product>> getAllProductsByCategoryResponse (String category, Pageable pageable){
        try {
            Page<Product> products = productService.getallProductsByCategory(category,pageable);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            throw new CustomException("Error occurred when getting all products with category : " + category ,e);
        }
    }

    protected ResponseEntity<Product> getProductByIdResponse(String id){
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            throw new CustomException("Error occurred when getting product with id : " + id , e);
        }
    }

    protected ResponseEntity<Product> addProductResponse(CreateProductDTO createProductDTO){
        try {
            Product product = modelMapper.map(createProductDTO,Product.class);
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            throw new CustomException("Error occurred when adding new product",e);
        }
    }

    protected ResponseEntity<Product> updateProductByIdResponse(String id, CreateProductDTO createProductDTO) {
        try {
            Product product = modelMapper.map(createProductDTO,Product.class);
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            throw new CustomException("Error occurred when updating product with id : " + id , e);
        }
    }

    protected ResponseEntity<Product> patchProductByIdResponse(String id, PatchProductDTO patchProductDTO) {
        try {
            Product product = modelMapper.map(patchProductDTO,Product.class);
            Product updatedProduct = productService.patchProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            throw new CustomException("Error occurred when updating product with id : " + id , e);
        }
    }

    protected ResponseEntity<Void> deleteProductByIdResponse(String  id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new CustomException("Error occurred when deleting product with id : " + id , e);
        }
    }


}
