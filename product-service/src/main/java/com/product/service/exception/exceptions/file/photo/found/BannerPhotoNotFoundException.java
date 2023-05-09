package com.product.service.exception.exceptions.file.photo.found;

import com.product.service.exception.exceptions.file.photo.main.FileException;

public class BannerPhotoNotFoundException extends FileException {

    public static BannerPhotoNotFoundException create(Long id) {
        return new BannerPhotoNotFoundException("Banner photo not found by id: " + id);
    }

    public BannerPhotoNotFoundException() {
    }

    public BannerPhotoNotFoundException(String message) {
        super(message);
    }

    public BannerPhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BannerPhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    public BannerPhotoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
