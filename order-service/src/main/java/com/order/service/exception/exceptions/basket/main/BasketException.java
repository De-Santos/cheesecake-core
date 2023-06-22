package com.order.service.exception.exceptions.basket.main;

@SuppressWarnings("unused")
public class BasketException extends RuntimeException {

    public BasketException() {
    }

    public BasketException(String message) {
        super(message);
    }

    public BasketException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketException(Throwable cause) {
        super(cause);
    }

    public BasketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
