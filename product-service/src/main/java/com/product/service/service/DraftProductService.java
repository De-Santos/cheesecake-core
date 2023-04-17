package com.product.service.service;

import com.product.service.dto.draft.DraftProductDto;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.request.DraftRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DraftProductService {
    private final DraftRequestConstructor draftRequestConstructor;
    private final ProductChecker productChecker;

    public String newDraft() {
        log.info("Creating new product draft");
        return draftRequestConstructor.newDraft();
    }

    public DraftProductDto update(DraftProductDto draftProductDto) {
        log.info("Update draft product by id: {}", draftProductDto.getId());
        productChecker.checkDraft(draftProductDto);
        return draftRequestConstructor.update(draftProductDto);
    }

    public DraftProductDto delete(String id) {
        log.info("Delete draft product by id: {}", id);
        productChecker.checkDraftById(id);
        return draftRequestConstructor.delete(id);
    }
}
