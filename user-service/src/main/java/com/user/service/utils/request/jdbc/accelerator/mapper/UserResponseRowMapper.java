package com.user.service.utils.request.jdbc.accelerator.mapper;

import com.user.service.dto.user.UserResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResponseRowMapper implements RowMapper<UserResponse> {

    @Override
    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserResponse.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .secondName(rs.getString("second_name"))
                .blocked(rs.getBoolean("blocked"))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }
}
