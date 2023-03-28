package com.product.service.exceptions.request;

public class ProductNameOutOfBoundsException extends ProductRequestException{
    public ProductNameOutOfBoundsException() {
    }

    public ProductNameOutOfBoundsException(String message) {
        super(message);
    }

    public ProductNameOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNameOutOfBoundsException(Throwable cause) {
        super(cause);
    }

    public ProductNameOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
