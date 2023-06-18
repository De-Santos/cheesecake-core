package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.notification.service.dto.DirectNotificationRequest
import ua.notification.service.dto.DirectNotificationResponse
import ua.notification.service.entity.additional.NotifyType
import ua.notification.service.entity.additional.SendType
import ua.notification.service.service.DirectNotificationService

@RestController
@RequestMapping("/api/v1/notification/direct")
class DirectNotificationController(
    private val directNotificationService: DirectNotificationService
) : DirectNotificationApi {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/create")
    override fun createDirectNotification(@RequestParam type: NotifyType, @RequestBody notification: DirectNotificationRequest): ResponseEntity<DirectNotificationResponse> {
        log.info("New direct notification")
        return ResponseEntity.ok(directNotificationService.new(type, notification, SendType.CUSTOM))
    }

    @PostMapping("/create/force")
    override fun createForceDirectNotification(@RequestParam type: NotifyType, @RequestBody notification: DirectNotificationRequest): ResponseEntity<DirectNotificationResponse> {
        log.info("New force direct notification")
        return ResponseEntity.ok(directNotificationService.new(type, notification, SendType.FORCE))
    }

    @GetMapping("/{id}")
    override fun getDirectNotification(@PathVariable id: Long): ResponseEntity<DirectNotificationResponse> {
        log.info("Get direct notification by id: {}", id)
        return ResponseEntity.ok(directNotificationService.get(id))
    }

    @GetMapping("/")
    override fun getAllDirectNotification(): ResponseEntity<List<Long>> {
        log.info("Get all direct notification ids")
        return ResponseEntity.ok(directNotificationService.all())
    }
}