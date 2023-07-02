package com.product.service.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.product.service.dto.photo.additional.FileCollectionDto;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class DraftProductRequest {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("images")
    private FileCollectionDto images;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("create_date")
    private String createDate;
}
