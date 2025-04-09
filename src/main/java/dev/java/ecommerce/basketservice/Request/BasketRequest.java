package dev.java.ecommerce.basketservice.Request;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
