package com.product.service.exception.exceptions.product.invalid;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class ProductInvalidSailPriceException extends ProductRequestException {
    public ProductInvalidSailPriceException() {
    }

    public ProductInvalidSailPriceException(String message) {
        super(message);
    }

    public ProductInvalidSailPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductInvalidSailPriceException(Throwable cause) {
        super(cause);
    }

    public ProductInvalidSailPriceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
