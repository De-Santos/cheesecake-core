package com.order.service.exception.exceptions.basket.nullable;

import com.order.service.exception.exceptions.basket.main.BasketException;

@SuppressWarnings("unused")
public class BasketIdIsNullException extends BasketException {

    public static BasketIdIsNullException create() {
        return new BasketIdIsNullException("Basket id must be not null");
    }

    public BasketIdIsNullException() {
    }

    public BasketIdIsNullException(String message) {
        super(message);
    }

    public BasketIdIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketIdIsNullException(Throwable cause) {
        super(cause);
    }

    public BasketIdIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
