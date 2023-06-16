package com.user.service.utils.request.jdbc.accelerator.mapper;

import com.user.service.dto.user.UserNotificationSettingsResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNotificationSettigsResponseRowMapper implements RowMapper<UserNotificationSettingsResponse> {

    @Override
    public UserNotificationSettingsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserNotificationSettingsResponse.builder()
                .id(rs.getLong("user_id"))
                .emailNotification(rs.getBoolean("email_notification"))
                .smsNotification(rs.getBoolean("sms_notification"))
                .adsNotification(rs.getBoolean("ads_notification"))
                .build();
    }
}
