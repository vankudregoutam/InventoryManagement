package com.tek.exception.product;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String msg) {
        super(msg);
    }
}
