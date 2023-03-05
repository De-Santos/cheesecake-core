package com.file.receiver.filereceiver.controller.internal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file.receiver.filereceiver.service.PhotoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/file/photo")
public class InternalController implements InternalApi {
    private final PhotoService photoService;

    @Override
    @GetMapping("/is/{id}")
    public ResponseEntity<Boolean> fileExist(@PathVariable String id) {
        log.info("Exist file by id: {}", id);
        return ResponseEntity.ok(photoService.checkFileExists(id));
    }

}
