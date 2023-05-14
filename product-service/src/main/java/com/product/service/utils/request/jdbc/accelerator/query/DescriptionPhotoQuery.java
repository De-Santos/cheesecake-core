package com.product.service.utils.request.jdbc.accelerator.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DescriptionPhotoQuery {
    DUPLICATE_BY_ID("INSERT INTO description_photos (image, media_type, real_photo_name, file_collection_id) " +
            "SELECT image, media_type, real_photo_name, ? " +
            "FROM description_photos " +
            "WHERE id = ? ");
    public final String query;
}
