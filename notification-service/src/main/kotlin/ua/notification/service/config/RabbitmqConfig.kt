package ua.notification.service.config

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitmqConfig(
    val queueProperty: QueuePropertyConfig
) {
    @Bean
    fun taskQueue(): Queue {
        return Queue(queueProperty.name, true)
    }
}
