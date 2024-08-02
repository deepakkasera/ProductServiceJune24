package com.scaler.productservicejune24.repositories;

import com.scaler.productservicejune24.models.Product;
import com.scaler.productservicejune24.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product Repo should contain all the methods (CRUD) related to Product model.

    List<Product> findByPriceIsGreaterThan(Double price);
    //select * from products where price > ?

    List<Product> findProductByTitleLike(String word); // case sensitive
    //select * from products where title like '%iphone%'

    List<Product> findByTitleLikeIgnoreCase(String word); // case insensitive.

    List<Product> findTop5ByTitleContains(String word);
    //select * from products where title like '' LIMIT 5

//    List<Product> findTopByTitleContains(int top, String word);

    List<Product> findProductsByTitleContainsAndPriceGreaterThan(
            String word,
            Double price
    );

    List<Product> findProductsByTitleContainsOrderById(String word);

    Optional<Product> findById(Long id);

    @Override
    Page<Product> findAll(Pageable pageable);

    //HQL
    @Query("select p.id as id, p.title as title from Product p where p.id = :x")
    List<ProductWithIdAndTitle> randomSearchMethod(Long x);

    //SQL
    @Query(value = "select p.id as id, p.title as title from product p where p.id = :productId", nativeQuery = true)
    List<ProductWithIdAndTitle> randomSearchMethod2(Long productId);
}

/*
1. Repository should be an interface.
2. Repository should extend JPARepository.

Optional<Product> optionalProduct = productRepository.findById(100);

String title = p.getTitle(); // NPE


Read -> done

 */
