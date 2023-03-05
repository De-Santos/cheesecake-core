package com.product.service.productservice.exceptions;


public class PhotoNotFoundException extends RuntimeException{

    public PhotoNotFoundException(String message) {
        super(message);
    }
    
}