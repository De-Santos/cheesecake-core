package ua.cheesecake.notificationservice.controller;

import io.klogging.Klogging
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ua.cheesecake.notificationservice.domain.Subscription
import ua.cheesecake.notificationservice.dto.SubscriptionDto
import ua.cheesecake.notificationservice.service.SubscriptionService

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
