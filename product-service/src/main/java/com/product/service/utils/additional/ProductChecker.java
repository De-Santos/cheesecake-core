package com.product.service.utils.additional;

import com.product.service.dao.ArchiveProductRepository;
import com.product.service.dao.DraftProductRepository;
import com.product.service.dao.ProductRepository;
import com.product.service.dto.draft.DraftProductDto;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.additional.Photo;
import com.product.service.exception.exceptions.product.empty.EmptyDescriptionException;
import com.product.service.exception.exceptions.product.empty.EmptyNameException;
import com.product.service.exception.exceptions.product.empty.EmptyPhotosException;
import com.product.service.exception.exceptions.product.found.DraftProductNotFoundException;
import com.product.service.exception.exceptions.product.invalid.ProductInvalidPriceException;
import com.product.service.exception.exceptions.product.invalid.ProductInvalidSailPriceException;
import com.product.service.exception.exceptions.product.nullable.NullArgumentException;
import com.product.service.exception.exceptions.product.sintax.ProductNameOutOfBoundsException;
import com.product.service.exception.exceptions.product.sintax.ProductPhotosLimitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductChecker {
    private final ProductRepository productRepository;
    private final ArchiveProductRepository archiveProductRepository;
    private final DraftProductRepository draftProductRepository;
    // TODO: 3/26/2023 create configuration from database
    private static final Integer MAX_PRODUCT_NAME_LENGTH = 100;
    private static final Integer MIN_PRODUCT_NAME_LENGTH = 0;

    public void checkProductList(List<String> products) {
        List<String> unfoundedProducts = products.stream()
                .filter(it -> !productRepository.existsProductByVersionIdAndActiveIsTrue(it))
                .toList();
        if (!unfoundedProducts.isEmpty()) {
            throw new ProductNotFoundException(unfoundedProducts.toString());
        }
    }

    public boolean check(String versionId) {
        return productRepository.existsProductByVersionIdAndActiveIsTrue(versionId);
    }

    public void checkDraft(String id) {
        if(draftProductRepository.existsById(id)) throw new DraftProductNotFoundException("Draft product not found by id: " + id);
    }

    public void checkDraftRequest(DraftProduct draftProduct) {
        this.checkName(draftProduct.getName());
        this.checkPhoto(draftProduct.getImages().getAll());
        this.checkPrice(draftProduct.getPrice());
        this.checkDescription(draftProduct.getDescription());
    }

    private void checkName(String name) {
        if (name == null) throw new NoSuchElementException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (name.length() > MAX_PRODUCT_NAME_LENGTH
                || name.length() <= MIN_PRODUCT_NAME_LENGTH) throw new ProductNameOutOfBoundsException();
    }

    private void checkDescription(String description) {
        if (description.isEmpty()) throw new EmptyDescriptionException();
    }

    private void checkPhoto(List<Photo> photos) {
        if (photos == null) throw new NoSuchElementException();
        if (photos.isEmpty()) throw new EmptyPhotosException();
        if (photos.size() > 10) throw new ProductPhotosLimitException();
    }

    private void checkPrice(BigDecimal price) {
        if (price == null) throw new NoSuchElementException();
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductInvalidPriceException("Price can't be less than zero.");
    }

    public void check(ModifyingProductRequest modifyingProductRequest) {
        this.checkProduct(modifyingProductRequest);
        this.checkSail(modifyingProductRequest);
    }

    private void checkProduct(ModifyingProductRequest modifyingProductRequest) {
        if (!productRepository.existsProductByVersionIdAndActiveIsTrue(modifyingProductRequest.getVersionId()))
            throw new ProductNotFoundException("Product not found by id: " + modifyingProductRequest.getVersionId());

    }

    private void checkSail(ModifyingProductRequest modifyingProductRequest) {
        BigDecimal price = productRepository.findProductByVersionId(modifyingProductRequest.getVersionId())
                .orElseThrow(ProductNotFoundException::new)
                .getPrice();
        BigDecimal sailPrice = modifyingProductRequest.getSailPrice();
        if (price == null || sailPrice == null) throw new NullArgumentException("Argument can't be null");
        if (sailPrice.compareTo(price) >= 0 || sailPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductInvalidSailPriceException("Sail price can't be less than zero and bugger than product price.");
    }

    public void checkExistence(String versionId) {
        log.info("Checking versionId existence in product and archive product databases.");
        if (productRepository.existsById(versionId)) return;
        if (archiveProductRepository.existsById(versionId)) return;
        throw new ProductNotFoundException("Product not found by id: " + versionId);
    }

    // think about it
    public void checkDraft(DraftProductDto draftProductDto) {
        this.checkName(draftProductDto.getName());
        // FIXME: 4/4/2023
        this.checkPhoto(draftProductDto.getImages());
        this.checkPrice(draftProductDto.getPrice());
        this.checkDescription(draftProductDto.getDescription());
    }

    public void checkDraftById(String id) {
        if (Boolean.FALSE.equals(draftProductRepository.existsById(id)))
            throw new DraftProductNotFoundException("Draft product not found.");
    }
}
