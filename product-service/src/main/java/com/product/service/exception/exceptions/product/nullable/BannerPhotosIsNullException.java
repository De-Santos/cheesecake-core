package com.product.service.exception.exceptions.product.nullable;

import com.product.service.exception.exceptions.product.main.FileCollectionException;

public class BannerPhotosIsNullException extends FileCollectionException {
    public static BannerPhotosIsNullException create() {
        return new BannerPhotosIsNullException("Banner photos is null");
    }

    public BannerPhotosIsNullException() {
    }

    public BannerPhotosIsNullException(String message) {
        super(message);
    }

    public BannerPhotosIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BannerPhotosIsNullException(Throwable cause) {
        super(cause);
    }

    public BannerPhotosIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
