package dev.java.ecommerce.basketservice.Controller;

import dev.java.ecommerce.basketservice.Entity.Basket;
import dev.java.ecommerce.basketservice.Request.BasketRequest;
import dev.java.ecommerce.basketservice.Request.PaymentRequest;
import dev.java.ecommerce.basketservice.Service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@Tag(name = "Basket API")
public class BasketController {

    private final BasketService basketService;

    @PostMapping
    @Operation(summary = "Cria uma nova cesta")
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basketRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma cesta pelo ID")
    public ResponseEntity<Basket> findBasket(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma cesta existente")
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id, basketRequest));
    }

    @PutMapping("/{id}/payment")
    @Operation(summary = "Registra o pagamento de uma cesta")
    public ResponseEntity<Basket> payBasket(@PathVariable String id, @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id, paymentRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma cesta pelo ID")
    public ResponseEntity<Void> deleteBasket(@PathVariable String id) {
        basketService.deleteBasket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
