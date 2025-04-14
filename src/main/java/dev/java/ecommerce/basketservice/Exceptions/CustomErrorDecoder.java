package dev.java.ecommerce.basketservice.Exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String MethodKey, Response response) {
        switch (response.status()){
            case 400:
                return new DataNotFoundException("product not found");
            default:
                return new Exception("exception while getting product");
        }
    }
}
