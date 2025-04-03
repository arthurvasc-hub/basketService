package dev.java.ecommerce.basketservice.Service;

import dev.java.ecommerce.basketservice.Client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.Response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public List<PlatziProductResponse> allProducts(){
        return platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse productById(Long id){
        return platziStoreClient.getProductById(id);
    }

}
