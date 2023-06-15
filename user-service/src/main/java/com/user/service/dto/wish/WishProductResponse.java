package com.user.service.dto.wish;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class WishProductResponse {
    @JsonProperty("product_version_id")
    private UUID productVersionId;

    @JsonProperty("wish_list_id")
    private Long wishListId;
}
