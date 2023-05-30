package ua.notification.service

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableRabbit
@SpringBootApplication
class NotificationServiceApplication

fun main(args: Array<String>) {
    runApplication<NotificationServiceApplication>(*args)
}
