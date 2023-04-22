package com.product.service.utils.request;

import com.product.service.dao.DraftProductRepository;
import com.product.service.dto.photo.DraftProductDto;
import com.product.service.entity.DraftProduct;
import com.product.service.exception.exceptions.product.found.DraftProductNotFoundException;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DraftRequestConstructor {
    private final DraftProductRepository draftProductRepository;
    private final ProductChecker productChecker;
    private final Convertor convertor;

    public String newDraft() {
        log.info("Creating new product draft in database");
        return draftProductRepository.save(DraftProduct.create()).getId();
    }

    public DraftProductDto update(DraftProductDto draftProductDto) {
        log.info("Update draft product in database");
        productChecker.checkDraftById(draftProductDto.getId());
        DraftProduct draftProduct = this.get(draftProductDto.getId());
        return convertor.convert(draftProductRepository.save(convertor.updateConvert(draftProduct, draftProductDto)));
    }

    public DraftProductDto delete(String id) {
        log.info("Delete draft product from database by id: {}", id);
        DraftProduct draftProduct = draftProductRepository.findById(id)
                .orElseThrow(() -> new DraftProductNotFoundException("Draft product not found."));
        draftProductRepository.delete(draftProduct);
        return convertor.convert(draftProduct);
    }

    public DraftProduct get(String id) {
        log.info("Get draft product from database by id: {}", id);
        return draftProductRepository.findById(id)
                .orElseThrow(() -> new DraftProductNotFoundException("Draft product not found."));
    }

    public List<String> get() {
        log.info("Getting all draft products from database");
        return draftProductRepository.findAll().stream()
                .map(DraftProduct::getId)
                .toList();
    }
}
