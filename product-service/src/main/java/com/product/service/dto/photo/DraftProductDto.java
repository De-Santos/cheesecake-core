package com.product.service.dto.photo;

import com.product.service.dto.photo.additional.FileCollectionDto;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class DraftProductDto {
    private Long id;
    private FileCollectionDto images;
    private String name;
    private String description;
    private BigDecimal price;
    private String createDate;
}
