package com.product.service.dto.product;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class ProductResponse {
    private String versionId;
    private String activeVersionId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal sailPrice;
    private String createDate;
    private boolean active;
}
