package com.product.service.dto.photo.additional;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class FileCollectionDto {
    @JsonProperty("banner_photo")
    private List<PhotoDto> bannerPhotos;
    @JsonProperty("description_photo")
    private PhotoDto descriptionPhoto;

}
