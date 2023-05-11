package com.product.service.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class SailProductRequest {
    private final UUID versionId;
    private final BigDecimal sailPrice;
}
