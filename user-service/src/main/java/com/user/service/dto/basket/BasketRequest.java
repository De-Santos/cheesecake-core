package com.user.service.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BasketRequest {
    @JsonProperty("user_id")
    private final String userId;

    @JsonProperty("product_version_id")
    private final String productVersionId;

    @JsonProperty("count")
    private final Integer count;
}
