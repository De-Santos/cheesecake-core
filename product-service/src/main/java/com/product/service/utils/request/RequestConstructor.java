package com.product.service.utils.request;

import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.Product;
import com.product.service.exception.exceptions.exist.ProductAlreadyExistException;
import com.product.service.exception.exceptions.found.ArchiveProductNotFoundException;
import com.product.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;
import ua.cheesecake.dto.product.ProductResponse;

import java.util.List;
import java.util.Optional;


@Log4j2
@Component
@RequiredArgsConstructor
public class RequestConstructor {

    private final Convertor convertor;
    private final ProductRepository productRepository;
    private final ArchiveProductRepository archiveProductRepository;

    public Product addProductIfNotExist(ProductRequest productRequest) {
        log.info("Saving product in database");
        if (productRepository.existsProductByName(productRequest.getName())) throw new ProductAlreadyExistException();
        Product product = productRepository.save(convertor.convert(productRequest));
        convertor.convertToFileInUse(product);
        return product;
    }

    public Product sailMode(ModifyingProductRequest modifyingProductRequest) {
        log.info("Sail mode for product: {}", modifyingProductRequest.getVersionId());
        log.debug("Sail price is: {}", modifyingProductRequest.getSailPrice());
        Product product = productRepository.findProductByVersionId(modifyingProductRequest.getVersionId())
                .orElseThrow(ProductNotFoundException::new);
        product.setSalePrice(modifyingProductRequest.getSailPrice());
        return productRepository.save(product);
    }

    public ProductResponse getPostById(String versionId) {
        log.info("Getting product from database by id: {}", versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        if (product.isPresent()) return convertor.convert(product.get());
        return convertor.convert(this.getArchiveProductByVersionId(versionId));
    }

    public Product getProductById(String id) {
        log.info("Getting product from database by id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found by id: " + id));
    }

    public List<ProductResponse> getAll() {
        log.info("Getting all products from database.");
        return productRepository
                .findAll()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    public ProductResponse updateProduct(String versionId, ProductRequest productRequest) {
        log.info("Updating product from database by id: {}", versionId);
        Product product = productRepository.findProductByVersionId(versionId)
                .orElseThrow(() -> new ProductNotFoundException("unknown versionId" + versionId));
        this.addArchiveProduct(product);
        Product newProduct = convertor.updateConvert(product, productRequest);
        return convertor.convert(productRepository.save(newProduct));
    }

    public void deleteAll() {
        log.info("Deleting all products from database.");
        productRepository.deleteAll();
        log.info("Deleting all archive product from database.");
        archiveProductRepository.deleteAll();
    }

    public ProductResponse editActive(String versionId) {
        Product product = productRepository.findProductByVersionId(versionId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found by versionId: " + versionId));
        log.debug("Edited product is: {}", product);
        product.setActive(!product.isActive());
        return convertor.convert(productRepository.save(product));
    }


    public void addArchiveProduct(Product product) {
        log.info("Adding archive product in database by id {}", product.getId());
        archiveProductRepository.save(convertor.convertToArchive(product));
    }

    public List<ProductResponse> getArchive() {
        log.info("Getting all archive products from database.");
        return archiveProductRepository
                .findAll()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    public ArchiveProduct getArchiveProductByVersionId(String versionId) {
        log.info("Getting archive product from database by version id: {}", versionId);
        ArchiveProduct archiveProduct = archiveProductRepository
                .findByVersionId(versionId)
                .orElseThrow(() ->
                        new ArchiveProductNotFoundException("Archive product not found by versionId: " + versionId));
        Product activeProduct = this.getProductById(archiveProduct.getActualProductId());
        archiveProduct.setActualProductId(activeProduct.getVersionId());
        return archiveProduct;
    }
}
