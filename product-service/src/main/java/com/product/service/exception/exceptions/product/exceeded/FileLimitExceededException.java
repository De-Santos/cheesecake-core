package com.product.service.exception.exceptions.product.exceeded;

import com.product.service.exception.exceptions.product.main.FileCollectionException;

public class FileLimitExceededException extends FileCollectionException {
    public FileLimitExceededException() {
    }

    public FileLimitExceededException(String message) {
        super(message);
    }

    public FileLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileLimitExceededException(Throwable cause) {
        super(cause);
    }

    public FileLimitExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
