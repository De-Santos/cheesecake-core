package com.product.service.utils.request;

import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.FileCollectionRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.product.SailProductRequest;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import com.product.service.exception.exceptions.product.found.ArchiveProductNotFoundException;
import com.product.service.exception.exceptions.product.found.DraftProductNotFoundException;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
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
    private final EntityDuplicator entityDuplicator;

    // FIXME: 4/22/2023
    public Product addProductByDraftId(DraftProduct draftProduct) {
        log.info("Saving product in database by draft product id: {}", draftProduct.getId());
        productChecker.checkProductNameExistence(draftProduct.getName());
        draftProductRepository.delete(draftProduct);
        Product saveProduct = productRepository.save(convertor.convertToProduct(draftProduct));
        fileCollectionRepository.saveAndFlush(saveProduct.getImages().product(saveProduct));
        return saveProduct;
    }

    // FIXME: 4/22/2023
    public Product sailMode(SailProductRequest sailProductRequest) {
        log.info("Sail mode for product: {}", sailProductRequest.getVersionId());
        log.debug("Sail price is: {}", sailProductRequest.getSailPrice());
        Product product = this.safeGetProduct(sailProductRequest.getVersionId());
        product.setSalePrice(sailProductRequest.getSailPrice());
        return productRepository.save(product);
    }

    // FIXME: 4/22/2023
    // TODO: 5/10/2023 I have a problem
    public ProductResponse getProductById(UUID versionId) {
        log.info("Getting product from database by id: {}", versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        if (product.isPresent()) return convertor.convert(product.get());
        return this.getArchiveProductByVersionId(versionId);
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
        return productRepository
                .findAll()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    // FIXME: 4/22/2023
    @Transactional
    public Product updateProduct(Long draftProductId) {
        DraftProduct draftProduct = this.safeGetDraftProduct(draftProductId);
        log.info("Updating product from database by version id: {}", draftProduct.getParentVersionId());
        Product product = this.safeGetProduct(draftProduct.getParentVersionId());
        entityDuplicator.copyToArchive(product);
        return this.mergeUpdate(product, draftProduct);
    }

    private Product mergeUpdate(Product product, DraftProduct draftProduct) {
        Product newProduct = convertor.updateConvert(product, draftProduct);
        Product savedProduct = productRepository.save(newProduct);
        fileCollectionRepository.save(draftProduct.getImages().product(savedProduct));
        return savedProduct;
    }


    // FIXME: 4/22/2023
    @Transactional
    public void deleteAll() {
        log.info("Deleting all products from database.");
        productRepository.deleteAll();
        log.info("Deleting all archive product from database.");
        archiveProductRepository.deleteAll();
    }

    // FIXME: 4/22/2023
    public ProductResponse editActive(UUID versionId) {
        log.info("Activate/Deactivate product by versionId: {}", versionId);
        Product product = this.safeGetProduct(versionId);
        product.setActive(!product.isActive());
        return convertor.convert(productRepository.save(product));
    }

    // FIXME: 4/22/2023
    public List<ProductResponse> getArchive() {
        log.info("Getting all archive products from database.");
        return archiveProductRepository
                .findAll()
                .stream()
                .map(convertor::convert)
                .toList();
    }

    // FIXME: 4/22/2023
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

    // TODO: 5/11/2023 test me
    public DraftProduct draftFrom(UUID versionId) {
        log.info("Making draft product by versionId: {}", versionId);
        productChecker.forceCheckDraftExistenceByParentId(versionId);
        Optional<Product> product = productRepository.findProductByVersionId(versionId);
        if (product.isPresent()) return entityDuplicator.copyToDraft(product.get());
        ArchiveProduct archiveProduct = this.safeGetArchiveProduct(versionId);
        return draftProductRepository.save(entityDuplicator.copyToDraft(archiveProduct));
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
