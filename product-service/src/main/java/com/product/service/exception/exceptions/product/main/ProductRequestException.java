package com.product.service.exception.exceptions.product.main;

public class ProductRequestException extends RuntimeException {
    public ProductRequestException() {
        super("exception");
    }

    public ProductRequestException(String message) {
        super(message);
    }

    public ProductRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductRequestException(Throwable cause) {
        super(cause);
    }

    public ProductRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
