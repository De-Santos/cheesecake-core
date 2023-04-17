package com.product.service.exception.exceptions.photo.found;

public class FileOrderNotFoundException extends RuntimeException {
    public FileOrderNotFoundException() {
    }

    public FileOrderNotFoundException(String message) {
        super(message);
    }

    public FileOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOrderNotFoundException(Throwable cause) {
        super(cause);
    }

    public FileOrderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
