package com.product.service.service;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductResponse;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.DraftRequestConstructor;
import com.product.service.utils.request.RequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final Convertor convertor;
    private final ProductChecker productChecker;
    private final RequestConstructor requestConstructor;
    private final DraftRequestConstructor draftRequestConstructor;

    public ProductResponse addProduct(String draftId) {
        log.info("Add product by draft product id: {}", draftId);
        DraftProduct draftProduct = draftRequestConstructor.get(draftId);
        productChecker.checkDraftData(draftProduct);
        Product product = requestConstructor.addProductIfNotExistByDraftProductId(draftProduct);
        return convertor.convert(product);
    }

    public ProductResponse getById(String versionId) {
        log.debug("getPostById supplied id is: {}", versionId);
        productChecker.checkExistence(versionId);
        return requestConstructor.getPostById(versionId);
    }

    public List<ProductResponse> getProducts() {
        log.info("Get all products");
        return requestConstructor.getAll();
    }

    public ProductResponse updateProduct(String id) {
        log.info("Updating product by id: {}", id);
        DraftProduct draftProduct = draftRequestConstructor.get(id);
        productChecker.check(id);
        productChecker.checkDraftData(draftProduct);
        return requestConstructor.updateProduct(draftProduct);
    }

    public ProductResponse editProduct(String versionId) {
        log.info("Editing active product by id: {}", versionId);
        productChecker.forceCheck(versionId);
        return requestConstructor.editActive(versionId);
    }

    public void deleteAll() {
        log.info("Deleting all");
        requestConstructor.deleteAll();
    }

    public ProductResponse sailMode(ModifyingProductRequest modifyingProductRequest) {
        log.info("Sail mode for product by versionId: {}", modifyingProductRequest.getVersionId());
        productChecker.check(modifyingProductRequest);
        return convertor.convert(requestConstructor.sailMode(modifyingProductRequest));
    }

    public List<ProductResponse> getArchiveProducts() {
        log.info("Getting all archive products.");
        return requestConstructor.getArchive();
    }

    public DraftProductDto toDraft(String versionId) {
        log.info("Making draft product from product by versionId: {}", versionId);
        productChecker.checkGlobalExistence(versionId);
        return convertor.convert(requestConstructor.draftFrom(versionId));
    }
}
