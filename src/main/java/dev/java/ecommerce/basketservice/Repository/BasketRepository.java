package dev.java.ecommerce.basketservice.Repository;

import dev.java.ecommerce.basketservice.Entity.Basket;
import dev.java.ecommerce.basketservice.Enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {

    Optional<Basket> findByClientAndStatus(Long client, Status status);
}
