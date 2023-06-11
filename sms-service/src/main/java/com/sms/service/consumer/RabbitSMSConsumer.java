package com.sms.service.consumer;

import com.sms.service.entity.Notification;
import com.sms.service.sms.SMSSender;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitSMSConsumer {
    private final SMSSender sender;

    @SneakyThrows
    @RabbitListener(
            queues = "${spring.rabbitmq.queue-name}",
            messageConverter = "notificationMessageConverter",
            ackMode = "NONE"
    )
    private void handleMessage(Notification notification) {
        sender.send(notification);
    }
}
