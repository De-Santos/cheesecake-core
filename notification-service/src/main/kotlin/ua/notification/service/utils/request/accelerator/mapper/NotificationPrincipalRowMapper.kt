package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import java.sql.ResultSet

class NotificationPrincipalRowMapper : RowMapper<NotificationPrincipal> {

    override fun mapRow(rs: ResultSet, rowNum: Int): NotificationPrincipal {
        return NotificationPrincipal(
            userId = rs.getLong("id"),
            email = rs.getString("email"),
            phone = rs.getString("phone_number"),
            username = rs.getString("name"),
            emailNotification = rs.getBoolean("email_notification"),
            smsNotification = rs.getBoolean("sms_notification")
        )
    }
}