package dev.java.ecommerce.basketservice.Service;

import dev.java.ecommerce.basketservice.Entity.Basket;
import dev.java.ecommerce.basketservice.Entity.Product;
import dev.java.ecommerce.basketservice.Enums.Status;
import dev.java.ecommerce.basketservice.Repository.BasketRepository;
import dev.java.ecommerce.basketservice.Request.BasketRequest;
import dev.java.ecommerce.basketservice.Response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketService(BasketRepository basketRepository, ProductService productService) {
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    public Basket createBasket(BasketRequest basketRequest){

        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                throw new IllegalArgumentException("There is already a open basket for this client");
                });

        List<Product> products = new ArrayList<>();
    basketRequest.products().forEach(productRequest ->{
                    PlatziProductResponse platziProductResponse = productService.productById(productRequest.id());
                    products.add(Product.builder()
                            .id(platziProductResponse.id())
                            .title(platziProductResponse.title())
                            .price(platziProductResponse.price())
                            .quantity(productRequest.quantity())
                            .build());
    });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();

       return basketRepository.save(basket);
    }

}
