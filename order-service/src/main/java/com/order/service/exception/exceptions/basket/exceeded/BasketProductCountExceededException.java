package com.order.service.exception.exceptions.basket.exceeded;

import com.order.service.exception.exceptions.basket.main.BasketException;

import java.util.UUID;

@SuppressWarnings("unused")
public class BasketProductCountExceededException extends BasketException {

    public static BasketProductCountExceededException create(UUID productVersionId, Long basketId, Integer maxProductCount) {
        return new BasketProductCountExceededException(
                String.format(
                        "Product count must be less than %s. Basket by id: %d has the product wish version id: %s.",
                        maxProductCount,
                        basketId,
                        productVersionId
                )
        );
    }

    public BasketProductCountExceededException() {
    }

    public BasketProductCountExceededException(String message) {
        super(message);
    }

    public BasketProductCountExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasketProductCountExceededException(Throwable cause) {
        super(cause);
    }

    public BasketProductCountExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
