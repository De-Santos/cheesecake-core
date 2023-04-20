package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@Document(collection = "products")
public final class Product {
    @Id
    private String id;
    private String versionId;
    private FileCollection images;
    private String descriptionImageId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private LocalDateTime createDate;
    private boolean active;
}
