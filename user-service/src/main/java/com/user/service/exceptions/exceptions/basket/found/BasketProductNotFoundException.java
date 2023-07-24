package com.user.service.exceptions.exceptions.basket.found;

import com.user.service.exceptions.exceptions.basket.main.BasketException;

import java.util.UUID;

@SuppressWarnings("unused")
public class BasketProductNotFoundException extends BasketException {

    public static BasketProductNotFoundException create(Long basketId, UUID versionId) {
        return new BasketProductNotFoundException(
                String.format("Basket product not found for basket id: %d and version id: %s.", basketId, versionId)
        );
    }

    public BasketProductNotFoundException() {
    }

    public BasketProductNotFoundException(String message) {
        super(message);
    }

    public BasketProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public BasketProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
