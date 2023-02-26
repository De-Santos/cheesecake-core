package com.product.service.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Data
@Builder
@Jacksonized
public class ProductRequest {
    private final String name;
    private final String description;
    private final List<String> imagesId;
    private final String descriptionImageId;
    private final Integer price;
    private final Boolean active;
}
