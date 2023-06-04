package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import ua.notification.service.entity.additional.ProcessStatus
import java.sql.ResultSet

class ProcessStatusRowMapper : RowMapper<ProcessStatus> {
    override fun mapRow(rs: ResultSet, rowNum: Int): ProcessStatus {
        return ProcessStatus.valueOf(rs.getString("process_status"))
    }
}