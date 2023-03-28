package com.product.service.utils.additional;

import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.exceptions.request.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductChecker {
    private final ProductRepository productRepository;
    private final PhotoChecker photoChecker;
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

    public void checkRequest(ProductRequest productRequest) {
        this.checkName(productRequest);
        this.checkPhoto(productRequest);
        this.checkPrice(productRequest);
        this.checkDescription(productRequest);
    }

    private void checkName(ProductRequest productRequest) {
        String name = productRequest.getName();
        if (name == null) throw new NoSuchElementException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (name.length() > MAX_PRODUCT_NAME_LENGTH
                || name.length() <= MIN_PRODUCT_NAME_LENGTH) throw new ProductNameOutOfBoundsException();
    }

    private void checkDescription(ProductRequest productRequest) {
        String description = productRequest.getDescription();
        if (description.isEmpty()) throw new EmptyDescriptionException();
    }

    private void checkPhoto(ProductRequest productRequest) {
        List<String> photos = productRequest.getImagesId();
        if (photos == null) throw new NoSuchElementException();
        if (photos.isEmpty()) throw new EmptyPhotosException();
        if (photos.size() > 9) throw new ProductPhotosLimitException();
        photoChecker.check(productRequest);
    }

    private void checkPrice(ProductRequest productRequest) {
        BigDecimal price = productRequest.getPrice();
        if (price == null) throw new NoSuchElementException();
        if (price.compareTo(BigDecimal.ZERO) < 0) throw new ProductInvalidPriceException();
    }

    public void check(ModifyingProductRequest modifyingProductRequest) {
        this.checkProduct(modifyingProductRequest);
        this.checkSail(modifyingProductRequest);
    }

    private void checkProduct(ModifyingProductRequest modifyingProductRequest) {
        if (!productRepository.existsProductByVersionIdAndActiveIsTrue(modifyingProductRequest.getVersionId()))
            throw new ProductNotFoundException(modifyingProductRequest.getVersionId());

    }

    private void checkSail(ModifyingProductRequest modifyingProductRequest) {
        BigDecimal price = productRepository.findProductByVersionId(modifyingProductRequest.getVersionId())
                .orElseThrow(ProductNotFoundException::new)
                .getPrice();
        BigDecimal sailPrice = modifyingProductRequest.getSailPrice();
        if (price == null || sailPrice == null) throw new NoSuchElementException();
        if (sailPrice.compareTo(price) >= 0 || sailPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductInvalidSailPriceException();
    }
}
