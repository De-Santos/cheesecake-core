package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.UpdateOrderRequestException;

@SuppressWarnings("unused")
public class OrderTotalPriceIsNullException extends UpdateOrderRequestException {

    public static OrderTotalPriceIsNullException create() {
        return new OrderTotalPriceIsNullException("Order total price must not be null.");
    }

    public OrderTotalPriceIsNullException() {
    }

    public OrderTotalPriceIsNullException(String message) {
        super(message);
    }

    public OrderTotalPriceIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderTotalPriceIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderTotalPriceIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
