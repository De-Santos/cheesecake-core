package com.product.service.exception.exceptions.file.photo.found;

import com.product.service.exception.exceptions.file.photo.main.FileException;

public class DescriptionPhotoNotFoundException extends FileException {
    public static DescriptionPhotoNotFoundException create(Long id) {
        return new DescriptionPhotoNotFoundException("Description photo not found by id: " + id);
    }

    public DescriptionPhotoNotFoundException() {
    }

    public DescriptionPhotoNotFoundException(String message) {
        super(message);
    }

    public DescriptionPhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DescriptionPhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    public DescriptionPhotoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
