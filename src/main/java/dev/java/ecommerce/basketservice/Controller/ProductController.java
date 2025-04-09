package dev.java.ecommerce.basketservice.Controller;

import dev.java.ecommerce.basketservice.Response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts(){
        return ResponseEntity.ok(service.allProducts());
    }


    @GetMapping("{id}")
    public ResponseEntity<PlatziProductResponse> getProductById (@PathVariable Long id){
        return ResponseEntity.ok(service.productById(id));
    }
}
