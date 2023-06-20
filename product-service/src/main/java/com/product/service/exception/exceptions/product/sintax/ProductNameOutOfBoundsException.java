package com.product.service.exception.exceptions.product.sintax;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

@SuppressWarnings("unused")
public class ProductNameOutOfBoundsException extends ProductRequestException {
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
