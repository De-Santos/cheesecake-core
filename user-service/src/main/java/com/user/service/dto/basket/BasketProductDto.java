package com.user.service.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class BasketProductDto {
    @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("count")
    private Integer count;
}
