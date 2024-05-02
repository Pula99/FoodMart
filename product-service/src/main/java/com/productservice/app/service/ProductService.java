package com.productservice.app.service;

import com.productservice.app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public interface ProductService {

     Page<Product> getAllProduct(Pageable pageable);

     Product addProduct(Product product) throws Exception;

     Product updateProduct(Integer id ,  Product product) throws Exception;

     Product patchProduct(Integer id, Product data) throws Exception;

     Product getProductById(Integer id) throws Exception;

     void deleteProduct(Integer id) throws Exception;
}
