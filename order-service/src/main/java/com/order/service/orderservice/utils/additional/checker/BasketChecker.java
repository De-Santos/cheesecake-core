package com.order.service.orderservice.utils.additional.checker;

import org.springframework.stereotype.Component;

import com.order.service.orderservice.dao.BasketRepository;
import com.order.service.orderservice.utils.convertor.Convertor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class BasketChecker {
   
    private final BasketRepository basketRepository;
    private final Convertor convertor;

    public void checkCreate(Long userId) {
        if (!basketRepository.existsById(userId)) {
            basketRepository.save(convertor.emptyBasket(userId));
            log.info("Force create basket for user: {} \n Because basket for this user doesn't found", userId);
        } else log.info("basket found user: {}");
    }

    public boolean check(Long userId) {
        if (!basketRepository.existsById(userId)) { 
            log.info("basket by userId: {} not found", userId);
            return false;
        } else return true;
    }
}

