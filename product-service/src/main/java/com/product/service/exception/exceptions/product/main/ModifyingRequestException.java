package com.product.service.exception.exceptions.product.main;

public class ModifyingRequestException extends RuntimeException {
    public ModifyingRequestException() {
    }

    public ModifyingRequestException(String message) {
        super(message);
    }

    public ModifyingRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModifyingRequestException(Throwable cause) {
        super(cause);
    }

    public ModifyingRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
