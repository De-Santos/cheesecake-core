package com.order.service.exception.exceptions.order.time.invalid;

import com.order.service.exception.exceptions.order.time.main.OrderTimeException;

@SuppressWarnings("unused")
public class OrderRequiredDoneTimeInvalidException extends OrderTimeException {

    public OrderRequiredDoneTimeInvalidException() {
    }

    public OrderRequiredDoneTimeInvalidException(String message) {
        super(message);
    }

    public OrderRequiredDoneTimeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRequiredDoneTimeInvalidException(Throwable cause) {
        super(cause);
    }

    public OrderRequiredDoneTimeInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
