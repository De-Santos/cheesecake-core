package com.product.service.dto.photo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoResponse {
    private final Long id;
    private final String realPhotoName;
}
