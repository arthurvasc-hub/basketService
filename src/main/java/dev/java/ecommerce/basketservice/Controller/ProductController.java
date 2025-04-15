package dev.java.ecommerce.basketservice.Controller;

import dev.java.ecommerce.basketservice.Response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product API")
public class ProductController {

    private final ProductService service;

    @GetMapping
    @Operation(summary = "Lista todos os produtos disponíveis")
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts(){
        return ResponseEntity.ok(service.allProducts());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto pelo ID")
    public ResponseEntity<PlatziProductResponse> getProductById (@PathVariable Long id){
        return ResponseEntity.ok(service.productById(id));
    }
}