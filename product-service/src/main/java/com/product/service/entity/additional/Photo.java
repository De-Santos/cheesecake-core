package com.product.service.entity.additional;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.bson.types.Binary;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class Photo {
    private UUID hash;
    private Integer order;
    private String mediaType;
    private String realPhotoName;
    private Binary image;
}
