package com.product.service.utils.request.jdbc.accelerator.mapper;

import com.product.service.entity.additional.BannerPhoto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BannerPhotoRowMapper implements RowMapper<BannerPhoto> {

    @Override
    public BannerPhoto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BannerPhoto.builder()
                .id(rs.getLong("id"))
                .fileCollection(null)
                .position(rs.getInt("position"))
                .mediaType(rs.getString("media_type"))
                .realPhotoName(rs.getString("real_photo_name"))
                .image(rs.getBytes("image"))
                .build();
    }
}
