package com.demo.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public final class BasketProduct {
    private Long id;
    private Long basketId;
    private UUID productVersionId;
    private Integer count;
}
