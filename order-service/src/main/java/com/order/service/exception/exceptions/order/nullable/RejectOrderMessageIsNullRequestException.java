package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.RejectOrderRequestException;

@SuppressWarnings("unused")
public class RejectOrderMessageIsNullRequestException extends RejectOrderRequestException {

    public static RejectOrderMessageIsNullRequestException create() {
        return new RejectOrderMessageIsNullRequestException("Reject order message must not be null.");
    }

    public RejectOrderMessageIsNullRequestException() {
    }

    public RejectOrderMessageIsNullRequestException(String message) {
        super(message);
    }

    public RejectOrderMessageIsNullRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RejectOrderMessageIsNullRequestException(Throwable cause) {
        super(cause);
    }

    public RejectOrderMessageIsNullRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
