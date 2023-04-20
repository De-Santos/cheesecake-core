package com.product.service.dto.photo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoResponse {
    private final String id;
    private final String realPhotoName;
}
