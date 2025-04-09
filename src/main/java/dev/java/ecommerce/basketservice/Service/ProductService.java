package dev.java.ecommerce.basketservice.Service;

import dev.java.ecommerce.basketservice.Client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.Response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> allProducts(){
        log.info("Getting all products");
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "products", key = "#productId")
    public PlatziProductResponse productById(Long productId){
        log.info("Getting product with productId: {} ", productId  );
        return platziStoreClient.getProductById(productId);
    }

}
