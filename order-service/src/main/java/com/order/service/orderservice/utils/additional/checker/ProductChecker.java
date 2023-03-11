package com.order.service.orderservice.utils.additional.checker;


import org.springframework.stereotype.Component;

import com.order.service.orderservice.utils.template.Template;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ua.cheesecake.dto.exception.ProductNotFoundException;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductChecker {

    private final Template template;

    public boolean check(String versionId) {
        log.debug("product check by id: {}", versionId);
        boolean result = template.isRealProduct(versionId);
        if (!result) throw new ProductNotFoundException();
        return result;
    }

}
