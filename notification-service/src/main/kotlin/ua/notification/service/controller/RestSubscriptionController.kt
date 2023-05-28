package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ua.notification.service.domain.Subscription
import ua.notification.service.dto.SubscriptionDto
import ua.notification.service.service.SubscriptionService


@RestController
class RestSubscriptionController(
    private val subscriptionService: SubscriptionService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getSubscription(userId: Long): SubscriptionDto {
        logger.info("Request on getting subscription for user with id: $userId")
        val findByUserId = subscriptionService.findByUserId(userId)
        logger.info("Subscription for user with id: $userId is: $findByUserId")
        return findByUserId
    }

    @PostMapping
    fun saveSubscription(subscription: SubscriptionDto): Subscription {
        logger.info("Request on saving subscription: $subscription")
        val saveSubscription = subscriptionService.saveSubscription(subscription)
        logger.info("Subscription saved: $saveSubscription")
        return saveSubscription
    }
}
