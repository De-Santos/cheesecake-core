package com.product.service.utils.request.jdbc.accelerator.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BannerPhotoQuery {
    DUPLICATE_BY_ID("INSERT INTO banner_photos (image, media_type, position, real_photo_name, file_collection_id) " +
            "SELECT image, media_type, position, real_photo_name, ? " +
            "FROM banner_photos " +
            "WHERE id = ? "),

    UPLOAD("INSERT INTO banner_photos (image, media_type, position, real_photo_name, file_collection_id) VALUES (?, ?, ?, ?, ?) RETURNING id"),

    UPDATE("UPDATE banner_photos SET file_collection_id=?, position=?, media_type=?, real_photo_name=?, image=? WHERE id=?"),

    SELECT_BY_ID("SELECT * " +
            "FROM banner_photos " +
            "WHERE id = ? ");

    public final String query;
}
