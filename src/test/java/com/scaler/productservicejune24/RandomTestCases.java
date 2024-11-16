package com.scaler.productservicejune24;

import com.scaler.productservicejune24.controllers.ProductController;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTestCases {
    @Autowired
    private ProductController productController;

    @Test
    void testAddMethod() throws ProductNotFoundException {
        // 3A -> Arrange, Act & Assert.

        int a = 10;
        int b = 14;

        int result = a + b + 1;

        assertEquals(24, result, "result should be 24");

//        assertThrows(
//                ProductNotFoundException.class,
//                () -> productController.getProductById(-1L)
//        );
//
//        assertTimeout(
//                Duration.ofMillis(100),
//                () -> productController.getAllProducts(10, 10)
//        );

//        Product product = productController.getProductById(1L).getBody();
//
//        assertNotNull(product);

//        assertInstanceOf(
//                Penguin.class,
//                () -> birdFactory.getBird("PENGUIN")
//        );

//        assert result == 34;
    }
}
