package ua.notification.service.utils.broker.converter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.support.converter.AbstractMessageConverter
import org.springframework.stereotype.Component
import ua.notification.service.entity.additional.notification.Notification

@Component
class NotificationMessageConverter(
    private val objectMapper: ObjectMapper
) : AbstractMessageConverter() {

    override fun fromMessage(message: Message): Notification {
        val payload = message.body
        return objectMapper.readValue(payload, Notification::class.java)
    }

    override fun createMessage(obj: Any, messageProperties: MessageProperties): Message {
        val payload = objectMapper.writeValueAsBytes(obj)
        return Message(payload, messageProperties)
    }
}