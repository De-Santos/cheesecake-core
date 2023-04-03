package com.product.service.utils.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class Template {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${file.receiver.url}")
    private String fileReceiverUrl;

    public Boolean isRealFile(String id) {
        log.info("File is checking by id: {}", id);
        ResponseEntity<Boolean> response = restTemplate.getForEntity(fileReceiverUrl + "/is/" + id, Boolean.class);
        return response.getBody();
    }

    public void makeFilesInUse(List<String> ids) {
        log.info("Making file in use by ids");
        restTemplate.postForLocation(fileReceiverUrl + "/use", Collections.singletonMap("ids", ids));
    }
}
