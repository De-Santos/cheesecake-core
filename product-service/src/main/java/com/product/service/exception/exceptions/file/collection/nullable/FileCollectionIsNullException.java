package com.product.service.exception.exceptions.file.collection.nullable;

import com.product.service.exception.exceptions.file.collection.main.FileCollectionException;

@SuppressWarnings("unused")
public class FileCollectionIsNullException extends FileCollectionException {
    public static FileCollectionIsNullException create() {
        return new FileCollectionIsNullException("File collection is null");
    }

    public FileCollectionIsNullException() {
    }

    public FileCollectionIsNullException(String message) {
        super(message);
    }

    public FileCollectionIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionIsNullException(Throwable cause) {
        super(cause);
    }

    public FileCollectionIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
