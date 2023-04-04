package com.product.service.exception.exceptions.product.found;

import com.product.service.exception.exceptions.product.main.ProductRequestException;

public class ArchiveProductNotFoundException extends ProductRequestException {
    public ArchiveProductNotFoundException() {
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
