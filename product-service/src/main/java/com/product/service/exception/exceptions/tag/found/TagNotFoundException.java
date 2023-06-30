package com.product.service.exception.exceptions.tag.found;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class TagNotFoundException extends TagException {

    public static TagNotFoundException create(Long id) {
        return new TagNotFoundException("Tag not found by id: " + id);
    }

    public TagNotFoundException() {
    }

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNotFoundException(Throwable cause) {
        super(cause);
    }

    public TagNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
