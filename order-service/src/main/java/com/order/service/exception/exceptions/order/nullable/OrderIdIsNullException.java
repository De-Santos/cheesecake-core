package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.OrderRequestException;

@SuppressWarnings("unused")
public class OrderIdIsNullException extends OrderRequestException {

    public static OrderIdIsNullException create() {
        return new OrderIdIsNullException("Order id must not be null.");
    }

    public OrderIdIsNullException() {
    }

    public OrderIdIsNullException(String message) {
        super(message);
    }

    public OrderIdIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderIdIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderIdIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
