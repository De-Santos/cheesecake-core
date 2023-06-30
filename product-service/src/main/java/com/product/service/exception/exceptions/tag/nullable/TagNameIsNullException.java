package com.product.service.exception.exceptions.tag.nullable;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class TagNameIsNullException extends TagException {

    public static TagNameIsNullException create() {
        return new TagNameIsNullException("Tag name must not be null");
    }

    public TagNameIsNullException() {
    }

    public TagNameIsNullException(String message) {
        super(message);
    }

    public TagNameIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNameIsNullException(Throwable cause) {
        super(cause);
    }

    public TagNameIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
