package com.product.service.exception.exceptions.file.collection.main;

public class FileCollectionUpdateException extends RuntimeException {

    public FileCollectionUpdateException() {
    }

    public FileCollectionUpdateException(String message) {
        super(message);
    }

    public FileCollectionUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionUpdateException(Throwable cause) {
        super(cause);
    }

    public FileCollectionUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
