package com.product.service.utils.request.jdbc.executor.additional.mapper;

import com.product.service.entity.additional.Photo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoRowMapper implements RowMapper<Photo> {
    @Override
    public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Photo.builder()
                .id(rs.getLong("id"))
                .position(rs.getInt("position"))
                .mediaType(rs.getString("media_type"))
                .realPhotoName(rs.getString("real_photo_name"))
                .image(rs.getBytes("image"))
                .build();
    }
}
