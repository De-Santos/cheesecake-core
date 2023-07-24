package com.product.service.exception.exceptions.tag.nullable;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class TagRequestIsNullException extends TagException {

    public static TagRequestIsNullException create() {
        return new TagRequestIsNullException("Tag request must not be null");
    }

    public TagRequestIsNullException() {
    }

    public TagRequestIsNullException(String message) {
        super(message);
    }

    public TagRequestIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagRequestIsNullException(Throwable cause) {
        super(cause);
    }

    public TagRequestIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
