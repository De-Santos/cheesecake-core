package com.product.service.exception.exceptions.tag.matching;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class TagNotMatchingToDraftProductException extends TagException {

    public static TagNotMatchingToDraftProductException create(Long tagId, Long draftId) {
        return new TagNotMatchingToDraftProductException("Tag with id: " + tagId + " not matching to draft with id: " + draftId);
    }

    public TagNotMatchingToDraftProductException() {
    }

    public TagNotMatchingToDraftProductException(String message) {
        super(message);
    }

    public TagNotMatchingToDraftProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNotMatchingToDraftProductException(Throwable cause) {
        super(cause);
    }

    public TagNotMatchingToDraftProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
