package com.product.service.exception.exceptions.product.modifying;

import com.product.service.exception.exceptions.product.main.ModifyingRequestException;

@SuppressWarnings("unused")
public class FileCollectionModifyingException extends ModifyingRequestException {
    public FileCollectionModifyingException() {
    }

    public FileCollectionModifyingException(String message) {
        super(message);
    }

    public FileCollectionModifyingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionModifyingException(Throwable cause) {
        super(cause);
    }

    public FileCollectionModifyingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
