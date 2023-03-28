package com.product.service.utils.request;

import com.product.service.dao.ProductRepository;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.Product;
import com.product.service.exceptions.ProductAlreadyExistsException;
import com.product.service.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.ProductNotFoundException;


@Log4j2
@Component
@RequiredArgsConstructor
public class ProductRequestConstructor {
    private final Convertor convertor;
    private final ProductRepository productRepository;

    public Product addProductIfNotExist(ProductRequest productRequest) {
        log.info("Saving product in database");
        if (productRepository.existsProductByName(productRequest.getName())) throw new ProductAlreadyExistsException();
        return productRepository.save(convertor.convert(productRequest));
    }

    public Product sailMode(ModifyingProductRequest modifyingProductRequest) {
        log.info("Sail mode for product: {}", modifyingProductRequest.getVersionId());
        log.debug("Sail price is: {}", modifyingProductRequest.getSailPrice());
        Product product = productRepository.findProductByVersionId(modifyingProductRequest.getVersionId())
                .orElseThrow(ProductNotFoundException::new);
        product.setSalePrice(modifyingProductRequest.getSailPrice());
        return productRepository.save(product);
    }

}
