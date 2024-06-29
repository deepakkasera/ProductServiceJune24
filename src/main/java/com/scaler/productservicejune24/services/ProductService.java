package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();
}
