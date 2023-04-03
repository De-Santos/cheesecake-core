package com.file.receiver.exceptions.exceptioins.exceeded;

import com.file.receiver.exceptions.exceptioins.file.FileException;

public class FileSizeExceededException extends FileException {
    public FileSizeExceededException() {
    }

    public FileSizeExceededException(String message) {
        super(message);
    }

    public FileSizeExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeExceededException(Throwable cause) {
        super(cause);
    }

    public FileSizeExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
