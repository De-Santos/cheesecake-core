package com.order.service.exception.exceptions.product.exist;

import com.order.service.exception.exceptions.product.main.OrderProductException;

import java.util.UUID;

@SuppressWarnings("unused")
public class ProductDoesNotExistInActiveProductsException extends OrderProductException {

    public static ProductDoesNotExistInActiveProductsException create(UUID versionId) {
        return new ProductDoesNotExistInActiveProductsException("active product does not exist with version id: " + versionId);
    }

    public ProductDoesNotExistInActiveProductsException() {
    }

    public ProductDoesNotExistInActiveProductsException(String message) {
        super(message);
    }

    public ProductDoesNotExistInActiveProductsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductDoesNotExistInActiveProductsException(Throwable cause) {
        super(cause);
    }

    public ProductDoesNotExistInActiveProductsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
