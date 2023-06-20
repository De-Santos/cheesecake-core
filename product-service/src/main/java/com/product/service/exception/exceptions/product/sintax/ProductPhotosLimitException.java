package com.product.service.exception.exceptions.product.sintax;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

@SuppressWarnings("unused")
public class ProductPhotosLimitException extends ProductRequestException {
    public ProductPhotosLimitException() {
    }

    public ProductPhotosLimitException(String message) {
        super(message);
    }

    public ProductPhotosLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductPhotosLimitException(Throwable cause) {
        super(cause);
    }

    public ProductPhotosLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
