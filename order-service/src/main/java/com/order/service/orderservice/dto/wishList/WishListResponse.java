package com.order.service.orderservice.dto.wishList;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class WishListResponse {
    private String id;
    private List<String> wishProductList;
}
