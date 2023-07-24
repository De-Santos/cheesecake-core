package com.order.service.exception.exceptions.order.found;

import com.order.service.exception.exceptions.order.main.OrderException;

@SuppressWarnings("unused")
public class OrderNotFoundException extends OrderException {

    public static OrderNotFoundException create(Long id) {
        return new OrderNotFoundException("Order not found by id: " + id);
    }

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(Throwable cause) {
        super(cause);
    }

    public OrderNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
