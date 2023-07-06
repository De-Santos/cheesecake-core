package com.product.service.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.product.service.dto.photo.additional.FileCollectionDto;
import com.product.service.dto.tag.TagResponse;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class ProductResponse {

    @JsonProperty("version_id")
    private UUID versionId;

    @JsonProperty("active_version_id")
    private UUID activeVersionId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("sail_price")
    private BigDecimal sailPrice;

    @JsonProperty("create_date")
    private String createDate;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("file_collection")
    private FileCollectionDto images;

    @JsonProperty("tags")
    private List<TagResponse> tags;
}
