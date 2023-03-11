package com.order.service.orderservice.utils.additional.checker;

import com.order.service.orderservice.utils.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ua.cheesecake.dto.exception.UserNotFoundException;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserChecker {

    private final Template template;

    public boolean check(Long userId) {
        log.debug("UserChecker.check check user by id: {}", userId);
        if (!template.isRealUser(userId)) throw new UserNotFoundException(userId.toString());
        else return true;
    }
}
