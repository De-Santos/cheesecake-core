package com.product.service.utils.request.utils.duplicator;

import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DuplicateConverter {
    public DraftProduct convert(@NotNull Product product) {
        return DraftProduct.builder()
                .parentVersionId(product.getVersionId())
                .images(null)
                .hash(UUID.randomUUID())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    public DraftProduct convert(@NotNull ArchiveProduct archiveProduct) {
        return DraftProduct.builder()
                .parentVersionId(archiveProduct.getVersionId())
                .images(null)
                .hash(UUID.randomUUID())
                .name(archiveProduct.getName())
                .description(archiveProduct.getDescription())
                .price(archiveProduct.getPrice())
                .createDate(LocalDateTime.now())
                .build();
    }

    public ArchiveProduct convertToArchive(@NotNull Product product) {
        return ArchiveProduct.builder()
                .versionId(product.getVersionId())
                .actualProductId(product.getId())
                .images(null)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(product.getCreateDate())
                .tags(null)
                .build();
    }


    public Product convertToProduct(Product product, DraftProduct draftProduct) {
        product.setVersionId(UUID.randomUUID());
        product.setName(draftProduct.getName());
        product.setDescription(draftProduct.getDescription());
        product.setPrice(draftProduct.getPrice());
        product.setCreateDate(LocalDateTime.now());
        product.setActive(product.isActive());
        return product;
    }

}
