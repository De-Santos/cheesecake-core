package com.product.service.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.product.service.dto.photo.additional.FileCollectionDto;
import com.product.service.dto.tag.TagResponse;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Jacksonized
public class DraftProductResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("images")
    private FileCollectionDto images;

    @JsonProperty("tags")
    private List<TagResponse> tags;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("create_date")
    private String createDate;
}
