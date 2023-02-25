package com.product.service.productservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private Collection<String> imagesId;
    private String descriptionImageId;
    private String name;
    private String description;
    private Integer price;
    private LocalDateTime createDate; // TODO: 2/25/2023 save time in local UTC
    private boolean active;
}
