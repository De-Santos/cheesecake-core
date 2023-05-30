package com.product.service.utils.request.jdbc.accelerator.mapper;

import com.product.service.entity.additional.DescriptionPhoto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DescriptionPhotoRowMapper implements RowMapper<DescriptionPhoto> {

    @Override
    public DescriptionPhoto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DescriptionPhoto.builder()
                .id(rs.getLong("id"))
                .fileCollection(null)
                .mediaType(rs.getString("media_type"))
                .realPhotoName(rs.getString("real_photo_name"))
                .image(rs.getBytes("image"))
                .build();
    }
}
