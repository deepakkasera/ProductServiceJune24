package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id, Product product);

    void deleteProduct(Long id);

    Product addNewProduct(Product product);
}
