package com.product.service.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Data
@Builder
@Jacksonized
public class PostDto {
    private String name;
    private String description;
    private Integer price;
    private Boolean active;
}
