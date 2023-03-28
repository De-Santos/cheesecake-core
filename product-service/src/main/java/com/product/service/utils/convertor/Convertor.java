package com.product.service.utils.convertor;

import com.product.service.dto.product.ProductRequest;
import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import ua.cheesecake.dto.additional.TimeMapper;
import ua.cheesecake.dto.product.ProductResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Convertor {
    private final TimeMapper timeMapper;

    public Product convert(ProductRequest productRequest) {
        return Product.builder()
                .versionId(UUID.randomUUID().toString())
                .imagesId(productRequest.getImagesId())
                .descriptionImageId(productRequest.getDescriptionImageId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .createDate(LocalDateTime.now())
                .active(productRequest.isActive())
                .build();
    }

    public ProductResponse convert(Product product) {
        return ProductResponse.builder()
                .versionId(product.getVersionId())
                .imagesId(product.getImagesId())
                .descriptionImageId(product.getDescriptionImageId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sailPrice(product.getSalePrice())
                .createDate(timeMapper.toTime(product.getCreateDate()))
                .active(product.isActive())
                .build();
    }

    public ProductResponse convert(ArchiveProduct archiveProduct) {
        return ProductResponse.builder()
                .versionId(archiveProduct.getVersionId())
                .imagesId(archiveProduct.getImagesId())
                .descriptionImageId(archiveProduct.getDescriptionImageId())
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(timeMapper.toTime(archiveProduct.getCreateDate()))
                .activeVersionId(archiveProduct.getActualProductId())
                .build();
    }

    public ArchiveProduct convertToArchive(Product product) {
        return ArchiveProduct.builder()
                .versionId(product.getVersionId())
                .actualProductId(product.getId())
                .imagesId(product.getImagesId())
                .descriptionImageId(product.getDescriptionImageId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(product.getCreateDate())
                .build();
    }

    public Product updateConvert(Product product, ProductRequest productRequest) {
        product.setVersionId(UUID.randomUUID().toString());
        product.setImagesId(productRequest.getImagesId());
        product.setDescriptionImageId(productRequest.getDescriptionImageId());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCreateDate(LocalDateTime.now());
        product.setActive(product.isActive());
        return product;
    }
}
