package com.user.service.utils.additional.checker.base;


import com.user.service.utils.convertor.Convertor;
import com.user.service.utils.request.BasketRequestConstructor;
import com.user.service.dao.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class BasketChecker {
    
    private final BasketRequestConstructor basketRequestConstructor;
    private final BasketRepository basketRepository;
    private final Convertor convertor;

    public void checkCreate(Long userId) {
        if (!basketRepository.existsById(userId)) {
            basketRepository.save(convertor.emptyBasket(userId));
            log.info("Force create basket for user: {} \n Because basket for this user doesn't found", userId);
        } else log.info("basket found user: {}", userId);
    }

    public boolean check(Long userId) {
        if (!basketRepository.existsById(userId)) { 
            log.info("basket by userId: {} not found", userId);
            return false;
        } else return true;
    }

    public void checkDelete(Long userId) {
        log.debug("BasketChecker.checkDelete delete basket by id: {}", userId);
        if (!basketRepository.existsById(userId)) return;
        basketRequestConstructor.deleteBasket(userId);
    }
}

