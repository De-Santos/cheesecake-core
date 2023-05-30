package ua.notification.service.config

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated

@Validated
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.queue")
class QueuePropertyConfig {
    @NotBlank(message = "Queue name must be defined")
    var name: String = ""
}