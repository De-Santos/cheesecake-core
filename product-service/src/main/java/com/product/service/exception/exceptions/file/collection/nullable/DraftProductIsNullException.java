package com.product.service.exception.exceptions.file.collection.nullable;

import com.product.service.exception.exceptions.file.collection.main.FileCollectionException;

@SuppressWarnings("unused")
public class DraftProductIsNullException extends FileCollectionException {
    public static DraftProductIsNullException create() {
        return new DraftProductIsNullException("Draft product is null");
    }

    public DraftProductIsNullException() {
    }

    public DraftProductIsNullException(String message) {
        super(message);
    }

    public DraftProductIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public DraftProductIsNullException(Throwable cause) {
        super(cause);
    }

    public DraftProductIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
