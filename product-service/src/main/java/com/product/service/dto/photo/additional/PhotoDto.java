package com.product.service.dto.photo.additional;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("real_photo_name")
    private String realPhotoName;
}
