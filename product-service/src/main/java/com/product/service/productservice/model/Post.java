package com.product.service.productservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;


@Data
@NoArgsConstructor
@Document(collection = "products")
public class Post {
    @Id
    private String id;
    private String photo;
    private String name;
    private String description;
    private Integer price;
    private LocalDate createDate;
    private Boolean active;

    public Post(String photo, String name, String description, Integer price, LocalDate createDate, Boolean active) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.active = active;
    }
}
