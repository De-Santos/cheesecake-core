package com.product.service.utils.additional;

import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.DraftProductDto;
import com.product.service.dto.product.SaleProductRequest;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.additional.FileCollection;
import com.product.service.exception.exceptions.file.collection.invalid.FileCollectionOrderException;
import com.product.service.exception.exceptions.file.collection.nullable.BannerPhotosIsNullException;
import com.product.service.exception.exceptions.file.collection.nullable.DraftProductIsNullException;
import com.product.service.exception.exceptions.file.collection.nullable.FileCollectionIsNullException;
import com.product.service.exception.exceptions.product.empty.EmptyDescriptionException;
import com.product.service.exception.exceptions.product.empty.EmptyNameException;
import com.product.service.exception.exceptions.product.empty.EmptyPhotosException;
import com.product.service.exception.exceptions.product.exist.ProductAlreadyExistDraftException;
import com.product.service.exception.exceptions.product.exist.ProductAlreadyExistException;
import com.product.service.exception.exceptions.product.found.DraftProductNotFoundException;
import com.product.service.exception.exceptions.product.invalid.InvalidProductNameException;
import com.product.service.exception.exceptions.product.invalid.ProductInvalidPriceException;
import com.product.service.exception.exceptions.product.invalid.ProductInvalidSailPriceException;
import com.product.service.exception.exceptions.product.nullable.NullArgumentException;
import com.product.service.exception.exceptions.product.sintax.ProductNameOutOfBoundsException;
import com.product.service.utils.protector.Protector;
import com.product.service.utils.request.jdbc.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductChecker {
    private final ProductRepository productRepository;
    private final JdbcAccelerator accelerator;
    // TODO: 3/26/2023 create configuration from database
    private static final Integer MAX_PRODUCT_NAME_LENGTH = 100;
    private static final Integer MIN_PRODUCT_NAME_LENGTH = 0;

    public void checkProductNameExistence(String name) {
        if (accelerator.existProductByName(name))
            throw ProductAlreadyExistException.create(name);
    }


    public void forceCheckProductExistence(UUID versionId) throws ProductNotFoundException {
        if (accelerator.existProductByVersionId(versionId)) return;
        throw ProductNotFoundException.create(versionId.toString());
    }

    public void forceCheckGlobalExistence(UUID versionId) {
        if (accelerator.existProductByVersionId(versionId)) return;
        if (accelerator.existArchiveByVersionId(versionId)) return;
        throw new ProductNotFoundException();
    }

    public void checkDraft(Long id) {
        if (accelerator.existDraftById(id)) return;
        throw new DraftProductNotFoundException("Draft product not found by id: " + id);
    }

    public void checkDraftData(DraftProduct draftProduct) {
        Protector.safeNull(draftProduct);
        this.checkName(draftProduct.getName());
        this.checkPhoto(draftProduct);
        this.checkPrice(draftProduct.getPrice());
        this.checkDescription(draftProduct.getDescription());
    }

    private void checkName(String name) {
        if (name == null) throw InvalidProductNameException.create(null);
        if (name.isEmpty()) throw new EmptyNameException();
        if (name.length() > MAX_PRODUCT_NAME_LENGTH || name.length() <= MIN_PRODUCT_NAME_LENGTH)
            throw new ProductNameOutOfBoundsException();
    }

    private void checkDescription(String description) {
        if (description.isEmpty()) throw new EmptyDescriptionException();
    }

    private void checkPhoto(DraftProduct draftProduct) {
        FileCollection fileCollection = Protector
                .nonNull(FileCollectionIsNullException.create(), draftProduct.getImages());
        if (fileCollection.getBannerPhotos() == null) throw BannerPhotosIsNullException.create();
        if (fileCollection.getBannerPhotos().isEmpty()) throw new EmptyPhotosException();
    }

    private void checkPrice(BigDecimal price) {
        if (price == null) throw new NoSuchElementException();
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductInvalidPriceException("Price can't be less than zero.");
    }

    public void check(SaleProductRequest saleProductRequest) {
        this.forceCheckProductExistence(saleProductRequest.getVersionId());
        this.checkSail(saleProductRequest);
    }


    private void checkSail(SaleProductRequest saleProductRequest) {
        BigDecimal price = productRepository.findProductByVersionId(saleProductRequest.getVersionId())
                .orElseThrow(() -> ProductNotFoundException.create(saleProductRequest.getVersionId().toString()))
                .getPrice();
        BigDecimal sailPrice = saleProductRequest.getSailPrice();
        if (price == null || sailPrice == null) throw new NullArgumentException("Argument can't be null");
        if (sailPrice.compareTo(price) >= 0 || sailPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductInvalidSailPriceException("Sail price can't be less than zero and bugger than product price.");
    }

    public void checkExistence(UUID versionId) {
        log.info("Checking versionId existence in product and archive product databases.");
        if (accelerator.existProductByVersionId(versionId)) return;
        if (accelerator.existArchiveByVersionId(versionId)) return;
        throw ProductNotFoundException.create(versionId.toString());
    }

    public void checkDraft(DraftProductDto draftProductDto) {
        this.checkDraftPhotoDto(draftProductDto);
    }

    private void checkDraftPhotoDto(DraftProductDto draftProductDto) {
        Protector.notNullRequired(DraftProductIsNullException.create(), draftProductDto);
        Set<Integer> set = new HashSet<>();
        draftProductDto.getImages().getBannerPhotos().forEach(it -> {
            if (set.contains(it.getPosition())) throw new FileCollectionOrderException("Invalid file order");
            set.add(it.getPosition());
        });
    }

    public void checkDraftById(Long id) {
        if (Boolean.FALSE.equals(accelerator.existDraftById(id)))
            throw DraftProductNotFoundException.create(id);
    }

    public void forceCheckDraftExistenceByParentId(UUID versionId) {
        if (accelerator.existDraftByParentVersionId(versionId))
            throw ProductAlreadyExistDraftException.create(versionId);
    }

    public void forceCheckDraftExistence(Long draftId) throws DraftProductNotFoundException {
        if (accelerator.existDraftById(draftId)) return;
        throw DraftProductNotFoundException.create(draftId);
    }
}
