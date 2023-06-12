package com.product.service.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class SaleProductRequest {
    @JsonProperty("version_id")
    private final UUID versionId;
    @JsonProperty("sail_price")
    private final BigDecimal sailPrice;
}
