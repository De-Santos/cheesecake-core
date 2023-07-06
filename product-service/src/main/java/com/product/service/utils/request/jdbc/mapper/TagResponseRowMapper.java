package com.product.service.utils.request.jdbc.mapper;

import com.product.service.dto.tag.TagResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagResponseRowMapper implements RowMapper<TagResponse> {

    @Override
    public TagResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TagResponse.builder()
                .id(rs.getLong("id"))
                .tagName(rs.getString("tag_name"))
                .build();
    }
}
