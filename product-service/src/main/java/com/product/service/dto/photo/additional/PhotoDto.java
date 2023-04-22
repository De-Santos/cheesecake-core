package com.product.service.dto.photo.additional;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class PhotoDto {
    private UUID hash;
    private Integer order;
    private String realPhotoName;
}
