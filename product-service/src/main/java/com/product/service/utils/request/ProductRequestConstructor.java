package com.product.service.utils.request;

import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.product.SaleProductRequest;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.exception.exceptions.product.found.ArchiveProductNotFoundException;
import com.product.service.exception.exceptions.product.found.DraftProductNotFoundException;
import com.product.service.utils.check.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import com.product.service.utils.request.utils.duplicator.EntityDuplicator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Log4j2
@Component
@RequiredArgsConstructor
public class ProductRequestConstructor {
    private final Convertor convertor;
    private final ProductChecker productChecker;
    private final ProductRepository productRepository;
    private final FileCollectionRepository fileCollectionRepository;
    private final ArchiveProductRepository archiveProductRepository;
    private final DraftProductRepository draftProductRepository;
    private final JdbcAccelerator accelerator;
    private final EntityDuplicator entityDuplicator;

    @Transactional
    public UUID addProductByDraftId(DraftProduct draftProduct) {
        log.info("Saving product in database by draft product id: {}", draftProduct.getId());
        productChecker.checkProductNameExistence(draftProduct.getName());
        draftProductRepository.delete(draftProduct);
        return this.createProductFromDraft(draftProduct).getVersionId();
    }

    public Product sailMode(SaleProductRequest saleProductRequest) {
        log.info("Sail mode for product: {}", saleProductRequest.getVersionId());
        log.debug("Sail price is: {}", saleProductRequest.getSailPrice());
        Product product = this.safeGetProduct(saleProductRequest.getVersionId());
        product.setSalePrice(saleProductRequest.getSailPrice());
        return productRepository.save(product);
    }

    public ProductResponse getProductById(UUID versionId) {
        log.info("Getting product from database by id: {}", versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        if (product.isPresent()) return convertor.convert(product.get());
        return this.getArchiveProductByVersionId(versionId);
    }

    public List<UUID> getAll() {
        log.info("Getting all products from database.");
        return accelerator.getAllVersionId();
    }

    @Transactional
    public UUID updateProduct(Long draftProductId) {
        DraftProduct draftProduct = this.safeGetDraftProduct(draftProductId);
        log.info("Updating product from database by version id: {}", draftProduct.getParentVersionId());
        draftProductRepository.delete(draftProduct);
        Optional<Product> product = productRepository.findProductByVersionId(draftProduct.getParentVersionId());
        if (product.isPresent()) {
            entityDuplicator.copyToArchive(product.get());
            return this.productMergeUpdate(product.get(), draftProduct).getVersionId();
        }
        return this.createProductFromDraft(draftProduct).getVersionId();
    }

    private Product createProductFromDraft(DraftProduct draftProduct) {
        Product newProduct = productRepository.save(convertor.convertToProduct(draftProduct));
        fileCollectionRepository.save(draftProduct.getImages().product(newProduct));
        return newProduct;
    }

    private Product productMergeUpdate(Product product, DraftProduct draftProduct) {
        Product updatedProduct = productRepository.save(convertor.updateConvert(product, draftProduct));
        fileCollectionRepository.save(draftProduct.getImages().product(updatedProduct));
        draftProductRepository.delete(draftProduct);
        return updatedProduct;
    }

    @Transactional
    public void deleteAll() {
        log.info("Deleting all products from database.");
        productRepository.deleteAll();
        log.info("Deleting all archive product from database.");
        archiveProductRepository.deleteAll();
    }

    public ProductResponse editActive(UUID versionId) {
        log.info("Activate/Deactivate product by versionId: {}", versionId);
        Product product = this.safeGetProduct(versionId);
        product.setActive(!product.isActive());
        return convertor.convert(productRepository.save(product));
    }

    public List<ProductResponse> getArchive() {
        log.info("Getting all archive products from database.");
        return archiveProductRepository
                .findAll()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    public ProductResponse getArchiveProductByVersionId(UUID versionId) {
        log.info("Getting archive product from database by version id: {}", versionId);
        ArchiveProduct archiveProduct = this.safeGetArchiveProduct(versionId);
        return convertor.convert(archiveProduct, this.getActualVersionIdById(archiveProduct.getActualProductId()));
    }

    private UUID getActualVersionIdById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.create(id.toString()))
                .getVersionId();
    }

    @Transactional
    public Long draftFrom(UUID versionId) {
        log.info("Making draft product by versionId: {}", versionId);
        productChecker.forceCheckDraftExistenceByParentId(versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        if (product.isPresent()) return entityDuplicator.copyToDraft(product.get()).getId();
        ArchiveProduct archiveProduct = this.safeGetArchiveProduct(versionId);
        return entityDuplicator.copyToDraft(archiveProduct).getId();
    }

    private Product safeGetProduct(UUID versionId) {
        return productRepository.findProductByVersionId(versionId)
                .orElseThrow(() -> ProductNotFoundException.create(versionId.toString()));
    }

    private DraftProduct safeGetDraftProduct(Long id) {
        return draftProductRepository.findById(id)
                .orElseThrow(() -> DraftProductNotFoundException.create(id));
    }

    private ArchiveProduct safeGetArchiveProduct(UUID versionId) {
        return archiveProductRepository.findByVersionId(versionId)
                .orElseThrow(() -> ArchiveProductNotFoundException.create(versionId.toString()));
    }

}
