package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.OrderRequestException;

@SuppressWarnings("unused")
public class OrderRequestIsNullException extends OrderRequestException {

    public static OrderRequestIsNullException create() {
        return new OrderRequestIsNullException("Order request must be not null");
    }

    public OrderRequestIsNullException() {
    }

    public OrderRequestIsNullException(String message) {
        super(message);
    }

    public OrderRequestIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRequestIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderRequestIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
