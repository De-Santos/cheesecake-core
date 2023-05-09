package com.product.service.dto.photo.additional;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoDto {
    private Long id;
    private Integer position;
    private String realPhotoName;
}
