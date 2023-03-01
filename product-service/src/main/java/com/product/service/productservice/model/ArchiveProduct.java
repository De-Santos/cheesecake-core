package com.product.service.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "archiveProduct")
public class ArchiveProduct {
    private String versionId;
    private String actualProductId;
    private Collection<String> imagesId;
    private String descriptionImageId;
    private String name;
    private String description;
    private Integer price;
    private LocalDateTime createDate;
}


