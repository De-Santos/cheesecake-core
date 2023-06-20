package com.product.service.service;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
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
    private final Convertor convertor;

    public Long newDraft() {
        log.info("Creating new product draft");
        return draftRequestConstructor.newDraft();
    }

    public DraftProductDto update(DraftProductDto draftProductDto) {
        log.info("Update draft product by id: {}", draftProductDto.getId());
        productChecker.checkDraft(draftProductDto);
        return draftRequestConstructor.update(draftProductDto);
    }

    @Transactional
    public DraftProductDto delete(Long id) {
        log.info("Delete draft product by id: {}", id);
        productChecker.checkDraftById(id);
        return draftRequestConstructor.delete(id);
    }

    @Transactional
    public DraftProductDto get(Long id) {
        log.info("Get draft by id: {}", id);
        return convertor.convert(draftRequestConstructor.get(id));
    }

    public List<Long> get() {
        log.info("Get all draft products");
        return draftRequestConstructor.get();
    }

}
