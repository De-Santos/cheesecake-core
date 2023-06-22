package com.order.service.exception.exceptions.basket.duplicate;

import com.order.service.exception.exceptions.basket.main.BasketException;

import java.util.UUID;

@SuppressWarnings("unused")
public class DuplicateBasketProductException extends BasketException {

    public static DuplicateBasketProductException create(UUID productVersionId) {
        return new DuplicateBasketProductException(
                String.format("Basket has duplicated product by id: %s", productVersionId)
        );
    }

    public DuplicateBasketProductException() {
    }

    public DuplicateBasketProductException(String message) {
        super(message);
    }

    public DuplicateBasketProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBasketProductException(Throwable cause) {
        super(cause);
    }

    public DuplicateBasketProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
