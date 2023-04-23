package com.product.service.utils.request;

import com.product.service.dao.DraftProductRepository;
import com.product.service.dto.photo.DraftProductDto;
import com.product.service.entity.DraftProduct;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DraftRequestConstructor {
    private final JdbcTemplate template;
    private final DraftProductRepository draftProductRepository;
    private final ProductChecker productChecker;
    private final Convertor convertor;

    // FIXME: 4/22/2023
    public String newDraft() {
//        log.info("Creating new product draft in database");
//        return draftProductRepository.save(DraftProduct.create()).getId();
        return null;
    }

    // FIXME: 4/22/2023
    public DraftProductDto update(DraftProductDto draftProductDto) {
//        log.info("Update draft product in database");
//        productChecker.checkDraftById(draftProductDto.getId());
//        DraftProduct draftProduct = this.get(draftProductDto.getId());
//        return convertor.convert(draftProductRepository.save(convertor.updateConvert(draftProduct, draftProductDto)));
        return null;
    }

    // FIXME: 4/22/2023
    public DraftProductDto delete(String id) {
//        log.info("Delete draft product from database by id: {}", id);
//        DraftProduct draftProduct = draftProductRepository.findById(id)
//                .orElseThrow(() -> new DraftProductNotFoundException("Draft product not found."));
//        draftProductRepository.delete(draftProduct);
//        return convertor.convert(draftProduct);
        return null;
    }

    // FIXME: 4/22/2023
    public DraftProduct get(String id) {
//        log.info("Get draft product from database by id: {}", id);
//        return draftProductRepository.findById(id)
//                .orElseThrow(() -> new DraftProductNotFoundException("Draft product not found."));
        return null;
    }

    // FIXME: 4/22/2023
    public List<String> get() {
//        log.info("Getting all draft products from database");
//        return draftProductRepository.findAll().stream()
//                .map(DraftProduct::getId)
//                .toList();
        return null;
    }
}
