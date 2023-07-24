package com.order.service.exception.exceptions.order.main;

@SuppressWarnings("unused")
public class UpdateOrderRequestException extends RuntimeException {

    public UpdateOrderRequestException() {
    }

    public UpdateOrderRequestException(String message) {
        super(message);
    }

    public UpdateOrderRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateOrderRequestException(Throwable cause) {
        super(cause);
    }

    public UpdateOrderRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
