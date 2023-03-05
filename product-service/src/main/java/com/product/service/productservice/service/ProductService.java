package com.product.service.productservice.service;

import com.product.service.productservice.dao.ProductRepository;
import com.product.service.productservice.dto.product.ProductRequest;
import ua.cheesecake.dto.ProductResponse;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.utils.additional.PhotoChecker;
import com.product.service.productservice.utils.convertor.Convertor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Convertor convertor;
    private final PhotoChecker photoChecker;
    private final ArchiveProductService archiveProductService;

    public ProductResponse addPost(ProductRequest productRequest) {
        photoChecker.checker(productRequest);
        Product product = convertor.convert(productRequest);
        return convertor.convert(productRepository.save(product));
    }

    public ProductResponse getPostById(String versionId) {
        log.debug("getPostById supplied id is: {}", versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        log.debug("Founded product is: {}", product);
        if (product.isEmpty())
            return convertor.convert(archiveProductService.getArchivePostById(versionId));
        return convertor.convert(product.get());
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
        log.debug("Edited product is: ", product);
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
                .parallelStream()
                .map(convertor::convert)
                .toList();
    }
}
