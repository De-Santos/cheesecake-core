package com.product.service.service;

import com.product.service.dto.product.DraftProductRequest;
import com.product.service.dto.product.DraftProductResponse;
import com.product.service.utils.check.ProductChecker;
import com.product.service.utils.converter.Converter;
import com.product.service.utils.request.DraftRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class DraftProductService {
    private final DraftRequestConstructor draftRequestConstructor;
    private final ProductChecker productChecker;
    private final Converter converter;

    public Long newDraft() {
        log.info("Creating new product draft");
        return draftRequestConstructor.newDraft();
    }

    public DraftProductResponse update(DraftProductRequest draftProductRequest) {
        log.info("Update draft product by id: {}", draftProductRequest.getId());
        productChecker.checkDraft(draftProductRequest);
        return draftRequestConstructor.update(draftProductRequest);
    }

    @Transactional
    public DraftProductResponse delete(Long id) {
        log.info("Delete draft product by id: {}", id);
        productChecker.checkDraftById(id);
        return draftRequestConstructor.delete(id);
    }

    @Transactional
    public DraftProductResponse get(Long id) {
        log.info("Get draft by id: {}", id);
        return converter.convert(draftRequestConstructor.get(id));
    }

    public List<Long> get() {
        log.info("Get all draft products");
        return draftRequestConstructor.get();
    }
}
