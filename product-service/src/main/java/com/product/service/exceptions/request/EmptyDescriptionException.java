package com.product.service.exceptions.request;


public class EmptyDescriptionException extends ProductRequestException {

    public EmptyDescriptionException() {
    }

    public EmptyDescriptionException(String message) {
        super(message);
    }

    public EmptyDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDescriptionException(Throwable cause) {
        super(cause);
    }

    public EmptyDescriptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
