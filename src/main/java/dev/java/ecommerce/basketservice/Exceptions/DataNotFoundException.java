package dev.java.ecommerce.basketservice.Exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException (String message){
        super(message);
    }
}
