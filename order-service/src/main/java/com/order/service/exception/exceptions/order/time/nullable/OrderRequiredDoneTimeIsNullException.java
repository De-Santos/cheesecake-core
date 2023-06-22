package com.order.service.exception.exceptions.order.time.nullable;

import com.order.service.exception.exceptions.order.time.main.OrderTimeException;

@SuppressWarnings("unused")
public class OrderRequiredDoneTimeIsNullException extends OrderTimeException {

    public static OrderRequiredDoneTimeIsNullException create() {
        return new OrderRequiredDoneTimeIsNullException("Required done time must be not null");
    }

    public OrderRequiredDoneTimeIsNullException() {
    }

    public OrderRequiredDoneTimeIsNullException(String message) {
        super(message);
    }

    public OrderRequiredDoneTimeIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRequiredDoneTimeIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderRequiredDoneTimeIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
