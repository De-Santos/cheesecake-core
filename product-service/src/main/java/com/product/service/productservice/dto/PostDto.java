package com.product.service.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Data
@Builder
@Jacksonized
public class PostDto {
    private String name;
    private String description;
    private List<String> imagesId;
    private String descriptionImageId;
    private Integer price;
    private Boolean active;
}
