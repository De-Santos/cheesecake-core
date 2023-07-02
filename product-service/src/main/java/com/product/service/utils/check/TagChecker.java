package com.product.service.utils.check;

import com.product.service.exception.exceptions.tag.found.TagNotFoundException;
import com.product.service.exception.exceptions.tag.matching.TagNotMatchingToDraftProductException;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagChecker {
    private final JdbcAccelerator accelerator;

    public void checkExistence(Long tagId) {
        if (accelerator.isTagExistById(tagId)) return;
        throw TagNotFoundException.create(tagId);
    }

    public void checkTagMatchingToDraftProduct(Long tagId, Long draftId) {
        this.checkExistence(tagId);
        if (accelerator.isTagMatchingToDraftProductById(tagId, draftId)) return;
        throw TagNotMatchingToDraftProductException.crate(tagId, draftId);
    }

    public void checkTagMatchingToProductExistence(Long tagId, Long draftId) {
        this.checkExistence(tagId);
        if (accelerator.isTagMatchingToDraftProductById(tagId, draftId)) throw TagNotMatchingToDraftProductException.crate(tagId, draftId);
    }
}
