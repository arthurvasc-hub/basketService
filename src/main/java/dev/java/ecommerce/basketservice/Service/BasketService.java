package dev.java.ecommerce.basketservice.Service;

import dev.java.ecommerce.basketservice.Entity.Basket;
import dev.java.ecommerce.basketservice.Entity.Product;
import dev.java.ecommerce.basketservice.Enums.Status;
import dev.java.ecommerce.basketservice.Exceptions.BusinessException;
import dev.java.ecommerce.basketservice.Exceptions.DataNotFoundException;
import dev.java.ecommerce.basketservice.Repository.BasketRepository;
import dev.java.ecommerce.basketservice.Request.BasketRequest;
import dev.java.ecommerce.basketservice.Request.PaymentRequest;
import dev.java.ecommerce.basketservice.Response.PlatziProductResponse;
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

    public Basket getBasketById(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Basket not found"));
    }

    private List<Product> getProducts(BasketRequest basketRequest) {
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
        return products;
    }
    public Basket createBasket(BasketRequest basketRequest){
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                throw new BusinessException("There is already a open basket for this client");
                });

        List<Product> products = getProducts(basketRequest);
        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();

        basket.calculateTotalPrice();

       return basketRepository.save(basket);
    }


    public Basket updateBasket(String id, BasketRequest basketRequest) {
        Basket basket = getBasketById(id);
        List<Product> products = getProducts(basketRequest);
        basket.setProducts(products);
        basket.calculateTotalPrice();

        return basketRepository.save(basket);
    }

    public Basket payBasket(String id, PaymentRequest paymentRequest){
        Basket basket = getBasketById(id);
        basket.setPaymentMethod(paymentRequest.paymentMethod());
        basket.setStatus(Status.SOLD);
        return basketRepository.save(basket);


    }

    public void deleteBasket(String id) {
        basketRepository.delete(getBasketById(id));
    }

}
