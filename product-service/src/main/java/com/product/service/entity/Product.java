package com.product.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public final class Product {
    @Id
    private String id;
    private String versionId;
    private List<String> imagesId;
    private String descriptionImageId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
    private LocalDateTime createDate;
    private boolean active;
}
