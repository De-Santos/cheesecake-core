package com.product.service.dto.photo;

import com.product.service.dto.photo.additional.FileCollectionDto;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class DraftProductDto {
    private String id;
    private FileCollectionDto images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}
