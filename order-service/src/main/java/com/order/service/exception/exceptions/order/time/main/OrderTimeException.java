package com.order.service.exception.exceptions.order.time.main;

@SuppressWarnings("unused")
public class OrderTimeException extends RuntimeException {

    public OrderTimeException() {
    }

    public OrderTimeException(String message) {
        super(message);
    }

    public OrderTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderTimeException(Throwable cause) {
        super(cause);
    }

    public OrderTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
