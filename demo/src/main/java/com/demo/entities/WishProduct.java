package com.demo.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class WishProduct {
    private Long id;
    private UUID productVersionId;
    private Long wishListId;
}

