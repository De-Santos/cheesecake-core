package com.product.service.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@Jacksonized
public class ProductRequest {
    private final String name;
    private final String description;
    private final List<String> imagesId;
    private final String descriptionImageId;
    private final BigDecimal price;
    private final boolean active;
}
