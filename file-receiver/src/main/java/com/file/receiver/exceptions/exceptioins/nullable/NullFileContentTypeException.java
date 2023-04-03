package com.file.receiver.exceptions.exceptioins.nullable;

import com.file.receiver.exceptions.exceptioins.file.NullFileException;

public class NullFileContentTypeException extends NullFileException {
    public NullFileContentTypeException() {
    }

    public NullFileContentTypeException(String message) {
        super(message);
    }

    public NullFileContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullFileContentTypeException(Throwable cause) {
        super(cause);
    }

    public NullFileContentTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
