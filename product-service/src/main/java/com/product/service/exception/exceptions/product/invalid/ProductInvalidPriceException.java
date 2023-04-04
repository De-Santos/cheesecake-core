package com.product.service.exception.exceptions.product.invalid;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class ProductInvalidPriceException extends ProductRequestException {
    public ProductInvalidPriceException() {
    }

    public ProductInvalidPriceException(String message) {
        super(message);
    }

    public ProductInvalidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductInvalidPriceException(Throwable cause) {
        super(cause);
    }

    public ProductInvalidPriceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
