package com.file.receiver.controller.internal;

import com.file.receiver.dto.FileUseRequest;
import com.file.receiver.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Override
    @PostMapping("/use")
    public void use(@RequestBody FileUseRequest fileUseRequest) {
        log.info("Set file in use by ids: {}", fileUseRequest.getIds());
        photoService.use(fileUseRequest.getIds());
    }
}
