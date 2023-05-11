package com.product.service.service;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.product.SailProductRequest;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.DraftRequestConstructor;
import com.product.service.utils.request.ProductRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final Convertor convertor;
    private final ProductChecker productChecker;
    private final ProductRequestConstructor productRequestConstructor;
    private final DraftRequestConstructor draftRequestConstructor;

    @Transactional
    public ProductResponse addProduct(Long draftId) {
        log.info("Add product by draft product id: {}", draftId);
        DraftProduct draftProduct = draftRequestConstructor.get(draftId);
        productChecker.checkDraftData(draftProduct);
        Product product = productRequestConstructor.addProductByDraftId(draftProduct);
        return convertor.convert(product);
    }

    public ProductResponse getById(UUID versionId) {
        log.debug("getPostById supplied id is: {}", versionId);
        productChecker.checkExistence(versionId);
        return productRequestConstructor.getProductById(versionId);
    }

    public List<ProductResponse> getProducts() {
        log.info("Get all products");
        return productRequestConstructor.getAll();
    }

    public ProductResponse updateProduct(Long id) {
        log.info("Updating product by id: {}", id);
        productChecker.check(id);
        return convertor.convert(productRequestConstructor.updateProduct(id));
    }

    public ProductResponse editProduct(UUID versionId) {
        log.info("Editing active product by id: {}", versionId);
        productChecker.forceCheckExistence(versionId);
        return productRequestConstructor.editActive(versionId);
    }

    @Transactional
    public void deleteAll() {
        log.info("Deleting all");
        productRequestConstructor.deleteAll();
    }

    public ProductResponse sailMode(SailProductRequest sailProductRequest) {
        log.info("Sail mode for product by versionId: {}", sailProductRequest.getVersionId());
        productChecker.check(sailProductRequest);
        return convertor.convert(productRequestConstructor.sailMode(sailProductRequest));
    }

    public List<ProductResponse> getArchiveProducts() {
        log.info("Getting all archive products.");
        return productRequestConstructor.getArchive();
    }

    public DraftProductDto toDraft(UUID versionId) {
        log.info("Making draft product from product by versionId: {}", versionId);
        productChecker.forceCheckGlobalExistence(versionId);
        return convertor.convert(productRequestConstructor.draftFrom(versionId));
    }
}
