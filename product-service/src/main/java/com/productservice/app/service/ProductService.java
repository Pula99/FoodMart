package com.productservice.app.service;

import com.productservice.app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public interface ProductService {

     Page<Product> getAllProducts(Pageable pageable);
     Product getProductById(String id);
     Page<Product> getallProductsByCategory(String category, Pageable pageable);
     Product addProduct(Product product);
     Product updateProduct(String id ,  Product product);
     Product patchProduct(String id, Product data);
     void deleteProduct(String id);
}
