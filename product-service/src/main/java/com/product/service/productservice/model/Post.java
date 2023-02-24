package com.product.service.productservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;


@Data
@NoArgsConstructor
@Document(collection = "products")
public class Post {
    public Post(Collection<String> imagesId, String descriptionImageId, String name, String description, Integer price, Integer salePrice, LocalDateTime createDate, boolean active) {
        this.imagesId = imagesId;
        this.descriptionImageId = descriptionImageId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.salePrice = salePrice;
        this.createDate = createDate;
        this.active = active;
    }

    @Id
    private String id;
    private Collection<String> imagesId;
    private String descriptionImageId;
    private String name;
    private String description;
    private Integer price;
    private Integer salePrice;
    private LocalDateTime createDate;
    private boolean active;

}
