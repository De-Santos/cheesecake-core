package com.product.service.utils.additional;

import com.product.service.entity.DraftProduct;
import com.product.service.exception.exceptions.photo.bounds.FileOutOfBoundsException;
import com.product.service.exception.exceptions.product.exceeded.ProductFileLimitExceededException;
import org.springframework.stereotype.Component;

@Component
public class FileChecker {
    // TODO: 4/16/2023 make configuration from database
    private static final Integer MAX_FILE_COUNT = 9;

    public void checkFileOrder(Integer position) {
        if (position > MAX_FILE_COUNT) {
            throw new FileOutOfBoundsException("Insert position can't be more than : " + MAX_FILE_COUNT);
        }
    }

    public void checkFileOrder(DraftProduct draftProduct) {
        if (draftProduct.getImages().getAll().size() >= MAX_FILE_COUNT) {
            throw new ProductFileLimitExceededException("Can't be more files than: " + MAX_FILE_COUNT);
        }
    }
}
