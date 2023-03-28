package com.product.service.exceptions.request;

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
