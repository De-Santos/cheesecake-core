package com.user.service.utils.additional.checker.base;


import com.user.service.dao.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class BasketChecker {

    private final BasketRepository basketRepository;

    public boolean check(Long userId) {
        if (!basketRepository.existsById(userId)) {
            log.info("basket by userId: {} not found", userId);
            return false;
        } else return true;
    }
}

