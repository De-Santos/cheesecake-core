package com.order.service.exception.exceptions.order.main;

@SuppressWarnings("unused")
public class RejectOrderRequestException extends RuntimeException {

    public RejectOrderRequestException() {
    }

    public RejectOrderRequestException(String message) {
        super(message);
    }

    public RejectOrderRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RejectOrderRequestException(Throwable cause) {
        super(cause);
    }

    public RejectOrderRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
