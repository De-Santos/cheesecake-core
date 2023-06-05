package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import ua.notification.service.entity.ProcessMetadata
import java.sql.ResultSet
import java.util.*

class ProcessMetadataRowMapper : RowMapper<ProcessMetadata> {
    override fun mapRow(rs: ResultSet, rowNum: Int): ProcessMetadata {
        return ProcessMetadata(
            id = rs.getLong("id"),
            task = null,
            startTime = rs.getDate("start_time"),
            endTime = rs.getDate("end_time"),
            elapsedTime = this.calculateElapsedTime(rs)
        )
    }

    private fun calculateElapsedTime(rs: ResultSet): Long? {
        val startTime: Date? = rs.getDate("start_time")
        val endTime: Date? = rs.getDate("end_time")
        if (startTime == null || endTime == null) return null
        return endTime.time - startTime.time
    }
}