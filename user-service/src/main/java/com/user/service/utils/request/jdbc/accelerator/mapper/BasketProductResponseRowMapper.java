package com.user.service.utils.request.jdbc.accelerator.mapper;

import com.user.service.dto.basket.BasketProductResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BasketProductResponseRowMapper implements RowMapper<BasketProductResponse> {

    @Override
    public BasketProductResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BasketProductResponse.builder()
                .id(rs.getLong("basket_id"))
                .productVersionId(rs.getObject("product_version_id", UUID.class))
                .count(rs.getInt("count"))
                .build();
    }
}
