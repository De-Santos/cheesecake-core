package com.order.service.exception.exceptions.order.main;

@SuppressWarnings("unused")
public class OrderRequestException extends RuntimeException {

    public OrderRequestException() {
    }

    public OrderRequestException(String message) {
        super(message);
    }

    public OrderRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRequestException(Throwable cause) {
        super(cause);
    }

    public OrderRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
