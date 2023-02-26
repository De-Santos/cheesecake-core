package com.product.service.productservice.utils.convertor;

import com.product.service.productservice.dto.ProductRequest;
import com.product.service.productservice.dto.ProductResponse;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.utils.mapper.TimeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductConvertor {

    private final TimeMapper mapper;

    public Product convert(ProductRequest productRequest) {
        return Product.builder()
                .imagesId(productRequest.getImagesId())
                .descriptionImageId(productRequest.getDescriptionImageId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .createDate(LocalDateTime.now())
                .active(productRequest.getActive())
                .build();
    }

    public ProductResponse convert(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .imagesId(product.getImagesId())
                .descriptionImageId(product.getDescriptionImageId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(mapper.toTime(product.getCreateDate()))
                .active(product.isActive())
                .build();
    }

    /**
     * @return compressed product with date from product
     */
    public Product convert(Product product, ProductRequest productRequest) {
        return Product.builder()
                .imagesId(productRequest.getImagesId())
                .descriptionImageId(productRequest.getDescriptionImageId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .createDate(product.getCreateDate())
                .active(productRequest.getActive())
                .build();
    }
}
