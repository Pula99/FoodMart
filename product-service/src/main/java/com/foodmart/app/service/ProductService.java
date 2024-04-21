package com.foodmart.app.service;

import com.foodmart.app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public interface ProductService {

     Page<Product> getAllProduct(Pageable pageable);

     Product addProduct(Product product) throws Exception;

     Product updateProduct(Integer id ,  Product product) throws Exception;

     Product patchProduct(Integer id, Product data) throws Exception;

     Product getProductById(Integer id) throws Exception;

     void deleteProduct(Integer id) throws Exception;
}
