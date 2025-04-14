package dev.java.ecommerce.basketservice.Request;

import dev.java.ecommerce.basketservice.Enums.PaymentMethod;

public record PaymentRequest(PaymentMethod paymentMethod) {
}
