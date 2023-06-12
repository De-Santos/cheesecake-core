package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import ua.notification.service.dto.NotificationResponse
import java.sql.ResultSet


class NotificationResponseRowMapper : RowMapper<NotificationResponse> {
    private val processStatusRowMapper: ProcessStatusRowMapper = ProcessStatusRowMapper()

    override fun mapRow(rs: ResultSet, rowNum: Int): NotificationResponse {
        return NotificationResponse(
            id = rs.getLong("task_id"),
            createTime = rs.getDate("create_time"),
            processStatus = processStatusRowMapper.mapRow(rs, rowNum),
            processMetadata = rs.getLong("process_metadata_id")
        )
    }
}