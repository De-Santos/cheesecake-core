package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import ua.notification.service.dto.DirectNotificationResponse
import java.sql.ResultSet

class DirectNotificationResponseRowMapper : RowMapper<DirectNotificationResponse> {
    private val processStatusRowMapper: ProcessStatusRowMapper = ProcessStatusRowMapper()

    override fun mapRow(rs: ResultSet, rowNum: Int): DirectNotificationResponse {
        return DirectNotificationResponse(
            id = rs.getLong("id"),
            userId = rs.getLong("user_id"),
            status = processStatusRowMapper.mapRow(rs, rowNum),
            creationTime = rs.getTimestamp("creation_time")
        )
    }
}