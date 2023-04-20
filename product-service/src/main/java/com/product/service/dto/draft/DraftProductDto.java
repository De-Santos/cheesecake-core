package com.product.service.dto.draft;

import com.product.service.entity.additional.FileCollection;
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
    private FileCollection images;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createDate;
}
