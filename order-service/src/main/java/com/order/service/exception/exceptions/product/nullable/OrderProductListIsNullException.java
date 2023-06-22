package com.order.service.exception.exceptions.product.nullable;

import com.order.service.exception.exceptions.product.main.OrderProductException;

@SuppressWarnings("unused")
public class OrderProductListIsNullException extends OrderProductException {

    public static OrderProductListIsNullException create() {
        return new OrderProductListIsNullException("Product list must be not null");
    }

    public OrderProductListIsNullException() {
    }

    public OrderProductListIsNullException(String message) {
        super(message);
    }

    public OrderProductListIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProductListIsNullException(Throwable cause) {
        super(cause);
    }

    public OrderProductListIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
