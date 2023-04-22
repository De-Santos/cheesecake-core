package com.product.service.dto.photo.additional;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class FileCollectionDto {
    private List<PhotoDto> bannerPhotos;
    private PhotoDto descriptionPhoto;

}
