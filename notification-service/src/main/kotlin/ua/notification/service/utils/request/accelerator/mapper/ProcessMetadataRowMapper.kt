package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import ua.notification.service.entity.ProcessMetadata
import java.sql.ResultSet

class ProcessMetadataRowMapper : RowMapper<ProcessMetadata> {
    override fun mapRow(rs: ResultSet, rowNum: Int): ProcessMetadata {
        return ProcessMetadata(
            id = rs.getLong("id"),
            task = null,
            startTime = rs.getTimestamp("start_time"),
            endTime = rs.getTimestamp("end_time"),
            usersProcessed = rs.getLong("users_processed")
        )
    }
}