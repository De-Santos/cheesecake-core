package com.product.service.exception.exceptions.product.main;

public class DraftRequestException extends RuntimeException {
    public DraftRequestException() {
    }

    public DraftRequestException(String message) {
        super(message);
    }

    public DraftRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DraftRequestException(Throwable cause) {
        super(cause);
    }

    public DraftRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
