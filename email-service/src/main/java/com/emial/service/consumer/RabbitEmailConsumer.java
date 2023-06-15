package com.emial.service.consumer;

import com.emial.service.email.EmailSender;
import com.emial.service.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitEmailConsumer {
    private final EmailSender sender;

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
