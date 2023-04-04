package com.product.service.exception.exceptions.product.empty;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class EmptyPhotosException extends ProductRequestException {
    public EmptyPhotosException() {
    }

    public EmptyPhotosException(String message) {
        super(message);
    }

    public EmptyPhotosException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyPhotosException(Throwable cause) {
        super(cause);
    }

    public EmptyPhotosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
