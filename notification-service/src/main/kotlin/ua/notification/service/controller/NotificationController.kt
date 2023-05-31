package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
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
        return ResponseEntity.ok(notificationService.createNew(notification))
    }
}
