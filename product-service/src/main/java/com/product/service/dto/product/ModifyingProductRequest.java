package com.product.service.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class ModifyingProductRequest {
    private final String versionId;
    private final BigDecimal sailPrice;
}
