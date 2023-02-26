package com.product.service.productservice.utils.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeMapper {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public final String toTime(LocalDateTime time) {
        return dtf.format(time);
    }
}
