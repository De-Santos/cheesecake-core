package com.product.service.exception.exceptions.file.collection.update;

import com.product.service.exception.exceptions.file.collection.main.FileCollectionUpdateException;

@SuppressWarnings("unused")
public class FileCollectionNotIdentityUpdateException extends FileCollectionUpdateException {

    public FileCollectionNotIdentityUpdateException() {
    }

    public FileCollectionNotIdentityUpdateException(String message) {
        super(message);
    }

    public FileCollectionNotIdentityUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionNotIdentityUpdateException(Throwable cause) {
        super(cause);
    }

    public FileCollectionNotIdentityUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
