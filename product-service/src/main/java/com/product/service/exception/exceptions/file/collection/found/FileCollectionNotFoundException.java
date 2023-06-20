package com.product.service.exception.exceptions.file.collection.found;

import com.product.service.exception.exceptions.file.collection.main.FileCollectionException;

@SuppressWarnings("unused")
public class FileCollectionNotFoundException extends FileCollectionException {
    public static FileCollectionNotFoundException create(Long id) {
        return new FileCollectionNotFoundException("File collection not found by id: " + id);
    }

    public FileCollectionNotFoundException() {
    }

    public FileCollectionNotFoundException(String message) {
        super(message);
    }

    public FileCollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCollectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public FileCollectionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
