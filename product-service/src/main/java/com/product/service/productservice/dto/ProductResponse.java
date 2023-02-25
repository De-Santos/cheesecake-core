package com.product.service.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private Collection<String> imagesId;
    private String descriptionImageId;
    private String name;
    private String description;
    private Integer price;
    private String createDate;
    private boolean active;
}
