package com.product.service.exception.exceptions.tag.exist;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class TagNameAlreadyExistException extends TagException {

    public static TagNameAlreadyExistException create(String name) {
        return new TagNameAlreadyExistException("Tag already exist with name: " + name);
    }

    public TagNameAlreadyExistException() {
    }

    public TagNameAlreadyExistException(String message) {
        super(message);
    }

    public TagNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNameAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public TagNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
