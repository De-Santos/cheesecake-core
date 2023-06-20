package com.product.service.exception.exceptions.file.photo.main;

@SuppressWarnings("unused")
public class NullFileException extends RuntimeException {
    public NullFileException() {
    }

    public NullFileException(String message) {
        super(message);
    }

    public NullFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullFileException(Throwable cause) {
        super(cause);
    }

    public NullFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
