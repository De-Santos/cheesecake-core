package com.product.service.productservice.sevice;

import com.product.service.productservice.dao.ArchiveProductRepository;
import com.product.service.productservice.dao.ProductRepository;
import com.product.service.productservice.model.ArchiveProduct;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Log4j2
@Service
@RequiredArgsConstructor
public class ArchiveProductService {
    private final ArchiveProductRepository archiveProductRepository;
    private final ProductRepository productRepository;
    private final Convertor convertor;

    public ArchiveProduct getArchivePostById(String versionId) {
        ArchiveProduct archiveProduct = archiveProductRepository
                .findByVersionId(versionId)
                .orElseThrow(() -> new NoSuchElementException("Element not found by versionId: " + versionId));
        Product activeProduct = productRepository.findById(archiveProduct.getActualProductId()).orElseThrow();
        archiveProduct.setActualProductId(activeProduct.getVersionId());
        return archiveProduct;
    }

    public void addProduct(Product product) {
        archiveProductRepository.save(convertor.convertToArchive(product));
    }

    public void deleteAll() {
        archiveProductRepository.deleteAll();
    }

    public List<ArchiveProduct> getArchive() {
        return archiveProductRepository.findAll();
    }
}
