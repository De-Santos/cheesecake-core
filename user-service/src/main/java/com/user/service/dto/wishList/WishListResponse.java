package com.user.service.dto.wishList;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class WishListResponse {
    private String id;
    private List<String> wishProductList;
}
