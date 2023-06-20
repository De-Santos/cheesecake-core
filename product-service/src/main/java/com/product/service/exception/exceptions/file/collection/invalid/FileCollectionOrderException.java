package com.product.service.exception.exceptions.file.collection.invalid;

import com.product.service.exception.exceptions.file.collection.main.FileCollectionUpdateException;

@SuppressWarnings("unused")
public class FileCollectionOrderException extends FileCollectionUpdateException {
    public FileCollectionOrderException() {
    }

    public FileCollectionOrderException(String message) {
        super(message);
    }

    public FileCollectionOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionOrderException(Throwable cause) {
        super(cause);
    }

    public FileCollectionOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
