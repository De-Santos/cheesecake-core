package com.product.service.exception.exceptions.product.exist;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class ProductAlreadyExistException extends ProductRequestException {
    public ProductAlreadyExistException() {
    }

    public ProductAlreadyExistException(String message) {
        super(message);
    }

    public ProductAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public ProductAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
