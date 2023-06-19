package com.user.service.exceptions.exceptions.basket.found;

import com.user.service.exceptions.exceptions.basket.main.BasketException;

@SuppressWarnings("unused")
public class BasketNotFoundException extends BasketException {

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
