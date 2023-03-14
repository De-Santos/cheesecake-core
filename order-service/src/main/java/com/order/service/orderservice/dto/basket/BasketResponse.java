package com.order.service.orderservice.dto.basket;

import java.util.List;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BasketResponse {
    private Long userId;
    private List<BasketProductDto> basketProductList;
}
