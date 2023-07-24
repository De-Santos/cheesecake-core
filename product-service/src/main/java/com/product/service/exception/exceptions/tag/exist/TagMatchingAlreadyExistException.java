package com.product.service.exception.exceptions.tag.exist;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class TagMatchingAlreadyExistException extends TagException {

    public static TagMatchingAlreadyExistException create(Long tagId, Long draftId) {
        return new TagMatchingAlreadyExistException("Tag with id: " + tagId + " already exist in draft with id: " + draftId);
    }

    public TagMatchingAlreadyExistException() {
    }

    public TagMatchingAlreadyExistException(String message) {
        super(message);
    }

    public TagMatchingAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagMatchingAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public TagMatchingAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
