package com.product.service.utils.request;

import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.dto.product.DraftProductRequest;
import com.product.service.dto.product.DraftProductResponse;
import com.product.service.entity.DraftProduct;
import com.product.service.exception.exceptions.product.found.DraftProductNotFoundException;
import com.product.service.utils.check.FileCollectionChecker;
import com.product.service.utils.check.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DraftRequestConstructor {
    private final DraftProductRepository draftProductRepository;
    private final FileCollectionRequestConstructor fileCollectionRequestConstructor;
    private final TagCollectionRequestConstructor tagCollectionRequestConstructor;
    private final FileCollectionRepository fileCollectionRepository;
    private final FileCollectionChecker fileCollectionChecker;
    private final ProductChecker productChecker;
    private final JdbcAccelerator accelerator;
    private final Convertor convertor;

    // FIXME: 4/22/2023
    public Long newDraft() {
        log.info("Creating new product draft in database");
        DraftProduct draftProduct = draftProductRepository.save(DraftProduct.create());
        fileCollectionRequestConstructor.create(draftProduct);
        tagCollectionRequestConstructor.create(draftProduct);
        return draftProduct.getId();
    }

    // FIXME: 4/22/2023
    public DraftProductResponse update(DraftProductRequest draftProductRequest) {
        log.info("Update draft product in database");
        productChecker.checkDraftById(draftProductRequest.getId());
        DraftProduct draftProduct = this.get(draftProductRequest.getId());
        fileCollectionChecker.checkFileCollectionIdentity(draftProduct.getImages(), draftProductRequest.getImages());
        return convertor.convert(draftProductRepository.save(convertor.updateConvert(draftProduct, draftProductRequest)));
    }

    public DraftProductResponse delete(Long id) {
        log.info("Delete draft product from database by id: {}", id);
        DraftProduct draftProduct = draftProductRepository.findById(id)
                .orElseThrow(() -> DraftProductNotFoundException.create(id));
        draftProductRepository.delete(draftProduct);
        fileCollectionRepository.delete(draftProduct.getImages());
        return convertor.convert(draftProduct);
    }

    public DraftProduct get(Long id) {
        log.info("Get draft product from database by id: {}", id);
        return draftProductRepository.findById(id)
                .orElseThrow(() -> DraftProductNotFoundException.create(id));
    }

    public List<Long> get() {
        log.info("Getting all draft products from database");
        return accelerator.getAllDraftIds();
    }
}
