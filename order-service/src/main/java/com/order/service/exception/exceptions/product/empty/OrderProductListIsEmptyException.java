package com.order.service.exception.exceptions.product.empty;

import com.order.service.exception.exceptions.product.main.OrderProductException;

@SuppressWarnings("unused")
public class OrderProductListIsEmptyException extends OrderProductException {

    public static OrderProductListIsEmptyException create() {
        return new OrderProductListIsEmptyException("Product list must be not empty");
    }

    public OrderProductListIsEmptyException() {
    }

    public OrderProductListIsEmptyException(String message) {
        super(message);
    }

    public OrderProductListIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProductListIsEmptyException(Throwable cause) {
        super(cause);
    }

    public OrderProductListIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
