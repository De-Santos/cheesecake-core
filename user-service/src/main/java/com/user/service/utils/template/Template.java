package com.user.service.utils.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Component
@RequiredArgsConstructor
public class Template {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${product.service.url}")
    private String productServiceUrl;

    public Boolean isRealProduct(String versionId) {
        log.debug(productServiceUrl);
        ResponseEntity<Boolean> response = restTemplate.getForEntity(productServiceUrl + "/is/" + versionId, Boolean.class);
        log.info("Product checked by id: {} -- result is: {}", versionId, response);
        return response.getBody();
    }
}