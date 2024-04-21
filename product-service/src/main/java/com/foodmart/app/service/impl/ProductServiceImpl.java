package com.foodmart.app.service.impl;

import com.foodmart.app.model.Product;
import com.foodmart.app.repository.ProductRepository;
import com.foodmart.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        try {
            return productRepository.findAll(pageable);
        } catch (Exception exception){
            log.error("Error occurred when getting products");
            throw exception;
        }

    }

    @Override
    public Product getProductById(Integer id){
        try {
            return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        } catch (Exception exception) {
           log.error("Error occured when getting product with id {}",id, exception);
           throw exception;
        }
    }

    @Override
    public Product addProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception exception) {
            log.error("Error occurred when adding new product with name {}, error: {}", product.getProductName(), exception.getMessage());
            throw exception;
        }

    }

    @Override
    public Product updateProduct(Integer id, Product updatedProduct) throws Exception {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
            modelMapper.map(updatedProduct, product);
            return productRepository.save(product);
        } catch (Exception exception) {
            log.error("Error occurred when Updating product with name {}, error: {}", updatedProduct.getProductName(), exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Product patchProduct(Integer id, Product updatedProduct) throws Exception {
        try{
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
            modelMapper.map(updatedProduct, product);
            return productRepository.save(product);
        } catch (Exception exception) {
            log.error("Error occurred when Updating product with name {}, error: {}", updatedProduct.getProductName(), exception.getMessage());
            throw exception;
        }
    }

    @Override
    public void deleteProduct(Integer id) throws Exception {
        try {
            productRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error occurred when delete product with id {}, error: {}" ,id, exception.getMessage());
            throw exception;
        }
    }
}
