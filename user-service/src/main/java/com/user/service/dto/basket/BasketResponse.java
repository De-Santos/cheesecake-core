package com.user.service.dto.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class BasketResponse {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("basket_product_list")
    private List<BasketProductDto> basketProductList;
}
