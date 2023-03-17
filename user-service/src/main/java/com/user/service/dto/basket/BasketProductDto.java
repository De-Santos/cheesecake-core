package com.user.service.dto.basket;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BasketProductDto {
    private String productId;
    private Integer count;
}
