package com.product.service.entity;

import com.product.service.entity.additional.FileCollection;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "archiveProduct")
public final class ArchiveProduct {
    private String versionId;
    private String actualProductId;
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}
