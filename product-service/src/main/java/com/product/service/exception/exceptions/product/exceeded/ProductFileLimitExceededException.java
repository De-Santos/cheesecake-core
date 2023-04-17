package com.product.service.exception.exceptions.product.exceeded;

public class ProductFileLimitExceededException extends RuntimeException {
    public ProductFileLimitExceededException() {
    }

    public ProductFileLimitExceededException(String message) {
        super(message);
    }

    public ProductFileLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductFileLimitExceededException(Throwable cause) {
        super(cause);
    }

    public ProductFileLimitExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
