package com.product.service.dto.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoResponse {
    @JsonProperty("id")
    private final Long id;

    @JsonProperty("real_photo_name")
    private final String realPhotoName;
}
