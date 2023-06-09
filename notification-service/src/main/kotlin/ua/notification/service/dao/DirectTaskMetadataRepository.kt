package ua.notification.service.dao

import org.springframework.data.jpa.repository.JpaRepository
import ua.notification.service.entity.DirectTaskMetadata

interface DirectTaskMetadataRepository : JpaRepository<DirectTaskMetadata, Long>