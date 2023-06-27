package com.demo.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class WishList {
    private Long id;
    private Long userId;
}
