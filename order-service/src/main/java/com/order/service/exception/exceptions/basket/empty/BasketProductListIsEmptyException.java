package com.order.service.exception.exceptions.basket.empty;

import com.order.service.exception.exceptions.basket.main.BasketException;

@SuppressWarnings("unused")
public class BasketProductListIsEmptyException extends BasketException {

    public static BasketProductListIsEmptyException create() {
        return new BasketProductListIsEmptyException("Basket product list must be not empty");
    }

    public BasketProductListIsEmptyException() {
    }

    public BasketProductListIsEmptyException(String message) {
        super(message);
    }

    public BasketProductListIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketProductListIsEmptyException(Throwable cause) {
        super(cause);
    }

    public BasketProductListIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
