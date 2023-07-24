package com.order.service.exception.exceptions.product.nullable;

import com.order.service.exception.exceptions.product.main.OrderProductException;

@SuppressWarnings("unused")
public class OrderProductVersionIsNullException extends OrderProductException {

    public static OrderProductVersionIsNullException create() {
        return new OrderProductVersionIsNullException("Product version id must be not null");
    }

    public OrderProductVersionIsNullException() {
    }

    public OrderProductVersionIsNullException(String message) {
        super(message);
    }

    public OrderProductVersionIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProductVersionIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderProductVersionIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
