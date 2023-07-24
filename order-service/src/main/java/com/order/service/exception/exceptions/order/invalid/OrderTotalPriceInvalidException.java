package com.order.service.exception.exceptions.order.invalid;

import com.order.service.exception.exceptions.order.main.UpdateOrderRequestException;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class OrderTotalPriceInvalidException extends UpdateOrderRequestException {

    public static OrderTotalPriceInvalidException create(BigDecimal totalPrice) {
        return new OrderTotalPriceInvalidException("Order total price is invalid: " + totalPrice);
    }

    public OrderTotalPriceInvalidException() {
    }

    public OrderTotalPriceInvalidException(String message) {
        super(message);
    }

    public OrderTotalPriceInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderTotalPriceInvalidException(Throwable cause) {
        super(cause);
    }

    public OrderTotalPriceInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
