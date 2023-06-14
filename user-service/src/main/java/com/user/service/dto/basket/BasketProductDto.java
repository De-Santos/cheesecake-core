package com.user.service.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BasketProductDto {
    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("count")
    private Integer count;
}
