package com.user.service.utils.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
@RequiredArgsConstructor
public class Template {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Environment environment;

    public Boolean isRealProduct(String versionId) {
        ResponseEntity<Boolean> response = restTemplate.getForEntity(environment.getProperty("product.service.url") + "/is/" + versionId, Boolean.class);
        log.info("Product checked by id: {} -- result is: {}", versionId, response);
        return response.getBody();
    }
}