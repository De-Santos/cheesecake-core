package com.user.service.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class BasketProductResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("product_version_id")
    private UUID productVersionId;

    @JsonProperty("count")
    private Integer count;
}
