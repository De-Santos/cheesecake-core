package com.order.service.orderservice.utils.template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class Template {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${product.service.url}")
    private String productServiceUrl;

    // @Value("${user.service.url}")
    private String userServiceUrl;

    public Boolean isRealProduct(String versionId) {
        log.info(productServiceUrl);
        ResponseEntity<Boolean> response = restTemplate.getForEntity(productServiceUrl + "/is/" + versionId, Boolean.class);
        log.info("Product checked by id: {} \n result is: {}", versionId, response);
        return response.getBody();
    }

    public boolean isRealUser(Long id) {
        // TODO implement in user-service
        // ResponseEntity<Boolean> response = restTemplate.getForEntity(userServiceUrl + "/is/" + id, Boolean.class);
        // log.info("User checked by id: {} \n result is: {}", id, response);
        log.info("User checked by id: {} \n result is: ", id);
        return true;
        // TODO uncoment me: return Boolean.TRUE.equals(response.getBody());
    }
}
