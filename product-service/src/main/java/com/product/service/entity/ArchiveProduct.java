package com.product.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "archiveProduct")
public final class ArchiveProduct {
    private String versionId;
    private String actualProductId;
    private Collection<String> imagesId;
    private String descriptionImageId;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}


