package com.product.service.productservice.utils.template;

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

    @Value("${file.receiver.url}")
    private String fileReceiverUrl;

    public Boolean isRealFile(String id) {
        ResponseEntity<Boolean> response = restTemplate.getForEntity(fileReceiverUrl + "/is/" + id, Boolean.class);
        log.info("File is checked successfully by id: {}", id);
        return response.getBody();
    }
}
