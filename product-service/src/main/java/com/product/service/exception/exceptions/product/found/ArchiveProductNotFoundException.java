package com.product.service.exception.exceptions.product.found;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class ArchiveProductNotFoundException extends ProductRequestException {

    public static ArchiveProductNotFoundException create(String versionId) {
        return new ArchiveProductNotFoundException("Archive product not found by versionId: " + versionId);
    }

    public ArchiveProductNotFoundException() {
        super();
    }

    public ArchiveProductNotFoundException(String message) {
        super(message);
    }

    public ArchiveProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchiveProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public ArchiveProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
