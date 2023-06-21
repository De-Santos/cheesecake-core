package com.product.service.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@Jacksonized
public class ProductRequest {
    @JsonProperty("name")
    private final String name;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("images_id")
    private final List<String> imagesId;

    @JsonProperty("description_image_id")
    private final String descriptionImageId;

    @JsonProperty("price")
    private final BigDecimal price;

    @JsonProperty("active")
    private final boolean active;
}
