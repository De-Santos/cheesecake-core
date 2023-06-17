package com.sms.service.sms;

import com.sms.service.entity.Notification;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SMSSender {

    @SneakyThrows
    public void send(Notification notification) {
        Thread.sleep(4000);
        log.info(
                String.format(
                        "Notify user by id: %d, message is: %s%n",
                        notification.getPrincipal().getUserId(),
                        notification.getMessage()
                )
        );
    }
}
