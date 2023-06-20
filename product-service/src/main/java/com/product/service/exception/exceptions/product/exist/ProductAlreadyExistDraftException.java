package com.product.service.exception.exceptions.product.exist;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

import java.util.UUID;

@SuppressWarnings("unused")
public class ProductAlreadyExistDraftException extends ProductRequestException {
    public static ProductAlreadyExistDraftException create(UUID versionId) {
        return new ProductAlreadyExistDraftException("Product already has draft, product versionId: " + versionId);
    }

    public ProductAlreadyExistDraftException() {
    }

    public ProductAlreadyExistDraftException(String message) {
        super(message);
    }

    public ProductAlreadyExistDraftException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistDraftException(Throwable cause) {
        super(cause);
    }

    public ProductAlreadyExistDraftException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
