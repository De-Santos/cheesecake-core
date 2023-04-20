package com.product.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Deprecated(since = "since: 4/4/2023", forRemoval = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "photos")
public class Photo {
    @Id
    private String id;
    private Boolean inUse;
    private String mediaType;
    private String realPhotoName;
    private Binary image;
}
