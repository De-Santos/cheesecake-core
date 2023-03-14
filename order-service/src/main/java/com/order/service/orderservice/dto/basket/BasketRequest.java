package com.order.service.orderservice.dto.basket;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class BasketRequest {
    @NotNull
    private final String userId;
    @NotNull
    private final String productId;
    @NotNull
    private final Integer count;
}
