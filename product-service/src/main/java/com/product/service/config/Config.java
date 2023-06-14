package com.product.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.cheesecake.dto.TimeMapper;

@Configuration
public class Config {
    @Bean
    public TimeMapper timeMapper() {
        return new TimeMapper();
    }
}
