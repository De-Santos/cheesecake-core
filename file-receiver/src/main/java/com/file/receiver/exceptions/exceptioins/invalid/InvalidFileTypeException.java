package com.file.receiver.exceptions.exceptioins.invalid;

import com.file.receiver.exceptions.exceptioins.file.FileException;

public class InvalidFileTypeException extends FileException {
    public InvalidFileTypeException() {
    }

    public InvalidFileTypeException(String message) {
        super(message);
    }

    public InvalidFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidFileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
