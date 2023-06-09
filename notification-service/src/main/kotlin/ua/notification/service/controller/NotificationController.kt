package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.notification.service.dto.*
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.service.NotificationService


@RestController
@RequestMapping("/api/v1/notification")
class NotificationController(
    private val notificationService: NotificationService,
) : NotificationApi {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/create")
    override fun createNotification(@RequestBody notification: NotificationRequest): ResponseEntity<NotificationResponse> {
        log.info("New notification for all users")
        return ResponseEntity.ok(notificationService.new(notification))
    }

    @GetMapping("/{id}")
    override fun getNotification(@PathVariable id: Long): ResponseEntity<NotificationResponse> {
        log.info("Get notification by id: {}", id)
        return ResponseEntity.ok(notificationService.get(id))
    }

    @GetMapping("/status/{id}")
    override fun getStatus(@PathVariable id: Long): ResponseEntity<ProcessStatus> {
        log.info("Get notification status by id: {}", id)
        return ResponseEntity.ok(notificationService.getStatus(id))
    }

    @GetMapping("/")
    override fun getAll(): ResponseEntity<List<Long>> {
        log.info("Get all ids")
        return ResponseEntity.ok(notificationService.all())
    }

    @GetMapping("/active")
    override fun getActive(): ResponseEntity<List<Long>> {
        log.info("Get all active notifications")
        return ResponseEntity.ok(notificationService.active())
    }

    @GetMapping("/status")
    override fun getAllByStatus(@RequestParam status: ProcessStatus): ResponseEntity<List<Long>> {
        log.info("Get all notifications by status: {}", status.name)
        return ResponseEntity.ok(notificationService.byStatus(status))
    }

    @GetMapping("/process/{id}")
    override fun getProcessMetadata(@PathVariable id: Long): ResponseEntity<ProcessMetadataResponse> {
        log.info("Get process metadata by id: {}", id)
        return ResponseEntity.ok(notificationService.processMetadata(id))
    }
}
