package com.product.service.utils.request.jdbc.accelerator.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BannerPhotoQuery {
    DUPLICATE_BY_ID("INSERT INTO banner_photos (image, media_type, position, real_photo_name, file_collection_id) " +
            "SELECT image, media_type, position, real_photo_name, ? " +
            "FROM banner_photos " +
            "WHERE id = ? ");
    public final String query;

}
