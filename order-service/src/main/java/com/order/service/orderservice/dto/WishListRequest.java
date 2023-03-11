package com.order.service.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class WishListRequest {
    private final String userId;
    private final String productVersionId;
}
