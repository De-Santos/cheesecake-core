package com.product.service.service;

import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.Product;
import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
