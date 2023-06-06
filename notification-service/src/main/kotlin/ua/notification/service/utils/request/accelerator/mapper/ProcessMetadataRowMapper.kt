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
            startTime = rs.getTimestamp("start_time"),
            endTime = rs.getTimestamp("end_time"),
            elapsedTime = this.calculateElapsedTime(rs)
        )
    }

    private fun calculateElapsedTime(rs: ResultSet): Long? {
        println("ProcessMetadataRowMapper.calculateElapsedTime")
        val startTime: Date? = rs.getTimestamp("start_time")
        val endTime: Date? = rs.getTimestamp("end_time")
        if (startTime == null) return null
        if (endTime == null) return Date().time - startTime.time
        return endTime.time - startTime.time
    }
}