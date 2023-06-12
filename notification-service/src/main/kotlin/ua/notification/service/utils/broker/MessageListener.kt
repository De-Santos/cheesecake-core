package ua.notification.service.utils.broker

import org.slf4j.LoggerFactory
//import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ua.notification.service.config.Property
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.utils.request.accelerator.JdbcAccelerator

@Component
class MessageListener(
    private val accelerator: JdbcAccelerator
) {
    private val log = LoggerFactory.getLogger(javaClass)

//    @RabbitListener(
//        queues = [Property.taskQueueName],
//        messageConverter = Property.messageTaskBeanName,
//        ackMode = "NONE"
//    )
    fun receive(task: MessageTask) {
        log.info("receive task")
        accelerator.createNotification(task)
    }
}
