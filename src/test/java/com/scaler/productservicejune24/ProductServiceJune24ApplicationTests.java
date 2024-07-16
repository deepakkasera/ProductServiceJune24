package com.scaler.productservicejune24;

import com.scaler.productservicejune24.projections.ProductWithIdAndTitle;
import com.scaler.productservicejune24.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceJune24ApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDBQueries() {
        List<ProductWithIdAndTitle> productWithIdAndTitles =
                productRepository.randomSearchMethod2(1L);

        for (ProductWithIdAndTitle product : productWithIdAndTitles) {
            System.out.println(product.getId() + " " + product.getTitle());
        }

        System.out.println("DEBUG");
    }

}
