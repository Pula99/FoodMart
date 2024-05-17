package com.productservice.app.service.impl;

import com.productservice.app.exception.CustomException;
import com.productservice.app.exception.NotFound;
import com.productservice.app.model.Product;
import com.productservice.app.repository.ProductRepository;
import com.productservice.app.service.ProductService;
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
    public Page<Product> getAllProducts(Pageable pageable) {
        try {
            return productRepository.findAll(pageable);
        } catch (Exception exception){
            log.error("Error occurred when getting products");
            throw new CustomException("Error occured when getting products",exception);
        }
    }

    @Override
    public Page<Product> getallProductsByCategory(String category, Pageable pageable){
        try {
            return productRepository.findProductByCategory(category, pageable);
        } catch (Exception exception){
            log.error("Error occurred when getting products");
            throw new CustomException("Error occurred when getting products with category :" + category,exception);
        }
    }

    @Override
    public Product getProductById(String id){
        try {
            return productRepository.findById(id).orElseThrow(() -> new NotFound("Product not found with id : %s", id ));
        } catch (NotFound e) {
            throw e;
        } catch (Exception exception) {
           log.error("Error occurred when getting product with id {}",id, exception);
           throw new CustomException("Error occurred when getting product with id : " + id, exception);
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
    public Product updateProduct(String id, Product updatedProduct){
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new NotFound("Product not found with id : %s", id));
            modelMapper.map(updatedProduct, product);
            return productRepository.save(product);
        } catch (NotFound notFound) {
            throw notFound;
        } catch (Exception exception) {
            log.error("Error occurred when Updating product with id {}, error: {}", updatedProduct.getId(), exception.getMessage());
            throw exception;
        }
    }

    @Override
    public Product patchProduct(String id, Product updatedProduct){
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new NotFound("Product not found with id : %s", id));
            modelMapper.map(updatedProduct, product);
            return productRepository.save(product);
        } catch (NotFound notFound) {
            throw notFound;
        } catch (Exception exception) {
            log.error("Error occurred when Updating product with id {}, error: {}", updatedProduct.getId(), exception.getMessage());
            throw exception;
        }
    }

    @Override
    public void deleteProduct(String id){
        try {
            productRepository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error occurred when delete product with id {}, error: {}" ,id, exception.getMessage());
            throw exception;
        }
    }
}
