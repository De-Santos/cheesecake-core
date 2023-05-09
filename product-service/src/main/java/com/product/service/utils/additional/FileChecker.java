package com.product.service.utils.additional;

import com.product.service.exception.exceptions.file.photo.bounds.FileOutOfBoundsException;
import org.springframework.stereotype.Component;

@Component
public class FileChecker {
    // TODO: 4/16/2023 make configuration from database
    private static final Integer MAX_FILE_COUNT = 9;

    public void checkFileCollectionBounds(Integer position) {
        if (position > MAX_FILE_COUNT) {
            throw new FileOutOfBoundsException("Insert position can't be more than : " + MAX_FILE_COUNT);
        }
    }
}
