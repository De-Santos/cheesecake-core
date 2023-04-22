package com.product.service.exception.exceptions.product.main;

public class FileCollectionException extends RuntimeException {
    public FileCollectionException() {
    }

    public FileCollectionException(String message) {
        super(message);
    }

    public FileCollectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionException(Throwable cause) {
        super(cause);
    }

    public FileCollectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
