package com.product.service.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private List<String> imagesId;
    private String descriptionImageId;
    private Integer price;
    private Boolean active;
}
