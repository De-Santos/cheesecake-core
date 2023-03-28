package com.product.service.service;

import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.Product;
import com.product.service.utils.additional.PhotoChecker;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;

import com.product.service.utils.request.ProductRequestConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.cheesecake.dto.exception.ProductNotFoundException;
import ua.cheesecake.dto.product.ProductResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Convertor convertor;
    private final ProductChecker productChecker;
    private final ProductRequestConstructor productRequestConstructor;
    private final ArchiveProductService archiveProductService;

    public ProductResponse addPost(ProductRequest productRequest) {
        productChecker.checkRequest(productRequest);
        return convertor.convert(productRequestConstructor.addProductIfNotExist(productRequest));
    }

    public ProductResponse getPostById(String versionId) {
        log.debug("getPostById supplied id is: {}", versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        log.debug("Founded product is: {}", product);
        if (product.isEmpty())
            return convertor.convert(archiveProductService.getArchivePostById(versionId));
        return convertor.convert(product.get());
    }

    public Boolean checkProduct(String versionId) {
        return productChecker.check(versionId);
    }

    public List<ProductResponse> getAll() {
        return productRepository
                .findAll()
                .parallelStream()
                .map(convertor::convert)
                .toList();
    }

    public ProductResponse update(String versionId, ProductRequest productRequest) {
        log.debug("Supplied in method update are versionId: {}, productRequest: {}", versionId, productRequest);
        Product product = productRepository.findProductByVersionId(versionId)
                .orElseThrow(() -> new NoSuchElementException("unknown versionId" + versionId));
        log.debug("NoUpdated product is: {}", product);
        archiveProductService.addProduct(product);
        Product newProduct = convertor.updateConvert(product, productRequest);
        log.debug("Updated product is: {}", newProduct);
        return convertor.convert(productRepository.save(newProduct));
    }

    public ProductResponse edit(String versionId) {
        Product product = productRepository.findProductByVersionId(versionId)
                .orElseThrow(() -> new NoSuchElementException("unknown versionId"));
        log.debug("Edited product is: {}", product);
        product.setActive(!product.isActive());
        return convertor.convert(productRepository.save(product));
    }

    public void deleteAll() {
        productRepository.deleteAll();
        archiveProductService.deleteAll();
    }

    public List<ProductResponse> getArchive() {
        return archiveProductService
                .getArchive()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    public void checkProductSequence(List<String> versionIdList) {
        productChecker.checkProductList(versionIdList);
    }

    public ProductResponse sailMode(ModifyingProductRequest modifyingProductRequest) {
        productChecker.check(modifyingProductRequest);
        return convertor.convert(productRequestConstructor.sailMode(modifyingProductRequest));
    }
}
