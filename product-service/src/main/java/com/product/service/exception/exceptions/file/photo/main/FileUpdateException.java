package com.product.service.exception.exceptions.file.photo.main;

public class FileUpdateException extends RuntimeException {
    public FileUpdateException() {
    }

    public FileUpdateException(String message) {
        super(message);
    }

    public FileUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUpdateException(Throwable cause) {
        super(cause);
    }

    public FileUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
