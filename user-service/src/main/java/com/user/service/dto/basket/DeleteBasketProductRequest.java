package com.user.service.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class DeleteBasketProductRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("product_version_id")
    private UUID productVersionId;
}
