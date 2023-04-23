package com.product.service.utils.request;

import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductResponse;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.exception.exceptions.product.exist.ProductAlreadyExistDraftException;
import com.product.service.exception.exceptions.product.exist.ProductAlreadyExistException;
import com.product.service.exception.exceptions.product.found.ArchiveProductNotFoundException;
import com.product.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;


@Log4j2
@Component
@RequiredArgsConstructor
public class RequestConstructor {

    private final Convertor convertor;
//    private final ProductRepository productRepository;
//    private final ArchiveProductRepository archiveProductRepository;
    private final DraftProductRepository draftProductRepository;

    // FIXME: 4/22/2023
    public Product addProductIfNotExistByDraftProductId(DraftProduct draftProduct) {
//        log.info("Saving product in database by draft product id: {}", draftProduct.getId());
//        if (productRepository.existsProductByName(draftProduct.getName())) throw new ProductAlreadyExistException();
//        return productRepository.save(convertor.convertToProduct(draftProduct));
        return null;
    }

    // FIXME: 4/22/2023
    public Product sailMode(ModifyingProductRequest modifyingProductRequest) {
//        log.info("Sail mode for product: {}", modifyingProductRequest.getVersionId());
//        log.debug("Sail price is: {}", modifyingProductRequest.getSailPrice());
//        Product product = productRepository.findProductByVersionId(modifyingProductRequest.getVersionId())
//                .orElseThrow(() -> ProductNotFoundException.create(modifyingProductRequest.getVersionId()));
//        product.setSalePrice(modifyingProductRequest.getSailPrice());
//        return productRepository.save(product);
        return null;
    }

    // FIXME: 4/22/2023
    public ProductResponse getPostById(String versionId) {
//        log.info("Getting product from database by id: {}", versionId);
//        Optional<Product> product = productRepository.findProductByVersionId(versionId);
//        if (product.isPresent()) return convertor.convert(product.get());
//        return convertor.convert(this.getArchiveProductByVersionId(versionId));
        return null;
    }

    // FIXME: 4/22/2023
    public Product getProductById(String id) {
        log.info("Getting product from database by id: {}", id);
//        return productRepository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException("Product not found by id: " + id));
        return null;
    }

    // FIXME: 4/22/2023
    public List<ProductResponse> getAll() {
        log.info("Getting all products from database.");
//        return productRepository
//                .findAll()
//                .stream()
//                .map(convertor::convert)
//                .toList();
        return null;
    }

    // FIXME: 4/22/2023
    public ProductResponse updateProduct(DraftProduct draftProduct) {
//        log.info("Updating product from database by id: {}", draftProduct.getParentId());
//        if (archiveProductRepository.existsByVersionId(draftProduct.getParentId()))
//            return convertor.convert(productRepository.save(convertor.convertToProduct(draftProduct)));
//        Product product = productRepository.findProductByVersionId(draftProduct.getParentId())
//                .orElseThrow(() -> ProductNotFoundException.create(draftProduct.getParentId()));
//        this.addArchiveProduct(product);
//        return convertor.convert(productRepository.save(convertor.updateConvert(product, draftProduct)));
        return null;
    }

    // FIXME: 4/22/2023
    public void deleteAll() {
        log.info("Deleting all products from database.");
//        productRepository.deleteAll();
        log.info("Deleting all archive product from database.");
//        archiveProductRepository.deleteAll();
    }

    // FIXME: 4/22/2023
    public ProductResponse editActive(String versionId) {
//        Product product = productRepository.findProductByVersionId(versionId)
//                .orElseThrow(() -> new ProductNotFoundException("Product not found by versionId: " + versionId));
//        log.debug("Edited product is: {}", product);
//        product.setActive(!product.isActive());
//        return convertor.convert(productRepository.save(product));
        return null;
    }


    // FIXME: 4/22/2023
    private void addArchiveProduct(Product product) {
        log.info("Adding archive product in database by id {}", product.getId());
//        archiveProductRepository.save(convertor.convertToArchive(product));
    }

    // FIXME: 4/22/2023
    public List<ProductResponse> getArchive() {
//        log.info("Getting all archive products from database.");
//        return archiveProductRepository
//                .findAll()
//                .stream()
//                .map(convertor::convert)
//                .toList();
        return null;
    }

    // FIXME: 4/22/2023
    public ArchiveProduct getArchiveProductByVersionId(String versionId) {
//        log.info("Getting archive product from database by version id: {}", versionId);
//        ArchiveProduct archiveProduct = archiveProductRepository
//                .findByVersionId(versionId)
//                .orElseThrow(() -> ArchiveProductNotFoundException.create(versionId));
//        Product activeProduct = this.getProductById(archiveProduct.getActualProductId());
//        archiveProduct.setActualProductId(activeProduct.getVersionId());
//        return archiveProduct;
        return null;
    }

    // FIXME: 4/22/2023
    public DraftProduct draftFrom(String versionId) {
//        log.info("Making draft product by versionId: {}", versionId);
//        if (draftProductRepository.existsByParentId(versionId))
//            throw ProductAlreadyExistDraftException.create(versionId);
//        Optional<Product> product = productRepository.findProductByVersionId(versionId);
//        if (product.isPresent()) return draftProductRepository.save(convertor.toDraft(product.get()));
//        ArchiveProduct archiveProduct = archiveProductRepository.findByVersionId(versionId)
//                .orElseThrow(() -> ArchiveProductNotFoundException.create(versionId));
//        return draftProductRepository.save(convertor.toDraft(archiveProduct));
        return null;
    }
}
