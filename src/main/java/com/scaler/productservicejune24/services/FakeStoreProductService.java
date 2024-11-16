package com.scaler.productservicejune24.services;

import com.scaler.productservicejune24.dtos.FakeStoreProductDto;
import com.scaler.productservicejune24.exceptions.ProductNotFoundException;
import com.scaler.productservicejune24.models.Category;
import com.scaler.productservicejune24.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
//@Primary
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate,
                                   RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//        throw new RuntimeException("Something went wrong");


        //Check if this product is available in REDIS or not ?
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + productId);

        //Cache HIT
        if (product != null) {
            return product;
        }

//        //Call FakeStore to fetch the Product with given id. => HTTP Call.
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id " + productId + " doesn't exist");
        }

        product = convertFakeStoreProductToProduct(fakeStoreProductDto);

        //Before returning the product, store it in the Redis.
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + productId, product);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        //Convert List of FakeStoreProductDto into List of Product.
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }

        return products;
    }

    //Partial Update
    @Override
    public Product updateProduct(Long id, Product product) {
        //PATCH
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        //PUT
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
