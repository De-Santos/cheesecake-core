package com.order.service.exception.exceptions.product.nullable;

import com.order.service.exception.exceptions.product.main.OrderProductException;

@SuppressWarnings("unused")
public class OrderProductCountIsNullException extends OrderProductException {

    public static OrderProductCountIsNullException create() {
        return new OrderProductCountIsNullException("Product count must be not null");
    }

    public OrderProductCountIsNullException() {
    }

    public OrderProductCountIsNullException(String message) {
        super(message);
    }

    public OrderProductCountIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProductCountIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderProductCountIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
