package com.user.sevice.userservice.utils.additional.checker;


import com.user.sevice.userservice.dao.BasketRepository;
import com.user.sevice.userservice.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

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

