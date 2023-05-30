package com.product.service.exception.exceptions.product.invalid;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class InvalidProductNameException extends ProductRequestException {
    public static InvalidProductNameException create(String name) {
        return new InvalidProductNameException("Obtained invalid name: " + name);
    }

    public InvalidProductNameException() {
    }

    public InvalidProductNameException(String message) {
        super(message);
    }

    public InvalidProductNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidProductNameException(Throwable cause) {
        super(cause);
    }

    public InvalidProductNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
