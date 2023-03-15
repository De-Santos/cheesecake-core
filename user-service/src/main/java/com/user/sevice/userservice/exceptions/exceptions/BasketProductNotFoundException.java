package com.user.sevice.userservice.exceptions.exceptions;

public class BasketProductNotFoundException extends RuntimeException {

    public BasketProductNotFoundException() {
    }

    public BasketProductNotFoundException(String message) {
        super(message);
    }

    public BasketProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public BasketProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
