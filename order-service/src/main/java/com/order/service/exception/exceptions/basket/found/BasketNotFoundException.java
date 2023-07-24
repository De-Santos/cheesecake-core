package com.order.service.exception.exceptions.basket.found;

import com.order.service.exception.exceptions.basket.main.BasketException;

@SuppressWarnings("unused")
public class BasketNotFoundException extends BasketException {

    public static BasketNotFoundException create(Long basketId) {
        return new BasketNotFoundException("Basket not found by id: " + basketId);
    }

    public BasketNotFoundException() {
    }

    public BasketNotFoundException(String message) {
        super(message);
    }

    public BasketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketNotFoundException(Throwable cause) {
        super(cause);
    }

    public BasketNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
