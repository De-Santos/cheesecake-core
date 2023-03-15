package com.user.sevice.userservice.dto.basket;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class BasketResponse {
    private Long userId;
    private List<BasketProductDto> basketProductList;
}
