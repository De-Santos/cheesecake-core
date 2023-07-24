package com.order.service.exception.exceptions.product.main;

@SuppressWarnings("unused")
public class OrderProductException extends RuntimeException {

    public OrderProductException() {
    }

    public OrderProductException(String message) {
        super(message);
    }

    public OrderProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProductException(Throwable cause) {
        super(cause);
    }

    public OrderProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
