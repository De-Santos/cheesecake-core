package com.order.service.orderservice.dto;

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
