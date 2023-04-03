package com.product.service.service;

import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.Product;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.RequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.cheesecake.dto.product.ProductResponse;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final Convertor convertor;
    private final ProductChecker productChecker;
    private final RequestConstructor requestConstructor;

    public ProductResponse addProduct(ProductRequest productRequest) {
        log.info("Add product");
        productChecker.checkRequest(productRequest);
        Product product = requestConstructor.addProductIfNotExist(productRequest);
        log.info(productRequest);
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

    public ProductResponse updateProduct(String versionId, ProductRequest productRequest) {
        log.info("Updating product by id: {}", versionId);
        productChecker.check(versionId);
        productChecker.checkRequest(productRequest);
        return requestConstructor.updateProduct(versionId, productRequest);
    }

    public ProductResponse editProduct(String versionId) {
        log.info("Editing active product by id: {}", versionId);
        productChecker.check(versionId);
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
}
