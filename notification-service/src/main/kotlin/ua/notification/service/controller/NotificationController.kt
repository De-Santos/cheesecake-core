package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.service.NotificationService


@RestController
@RequestMapping("/api/v1/notification")
class NotificationController(
    private val notificationService: NotificationService,
) : NotificationApi {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/create")
    override fun createNotification(notification: NotificationRequest): ResponseEntity<NotificationResponse> {
        log.info("New notification for all users")
        return ResponseEntity.ok(notificationService.new(notification))
    }

    @GetMapping("/info/{id}")
    override fun getInfo(@PathVariable id: Long): ResponseEntity<NotificationResponse> {
        log.info("Get notification  info")
        return ResponseEntity.ok(notificationService.info(id))
    }

    @GetMapping("/")
    override fun getAll(): ResponseEntity<List<Long>> {
        log.info("Get all ids")
        return ResponseEntity.ok(notificationService.all())
    }

    @GetMapping("/active")
    override fun getActive(): ResponseEntity<List<Long>> {
        return ResponseEntity.ok(notificationService.active())
    }

    @GetMapping("/{status}")
    override fun getAllByStatus(@PathVariable status: ProcessStatus): ResponseEntity<List<Long>> {
        return ResponseEntity.ok(notificationService.byStatus(status))
    }

}
