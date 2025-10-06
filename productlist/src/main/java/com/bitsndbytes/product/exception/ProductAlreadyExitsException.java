package com.bitsndbytes.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProductAlreadyExitsException extends RuntimeException{

    public ProductAlreadyExitsException(String message) {
        super(message);
    }
}
