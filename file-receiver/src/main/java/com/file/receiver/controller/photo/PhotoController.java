package com.file.receiver.controller.photo;

import com.file.receiver.dto.PhotoResponse;
import com.file.receiver.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file/photo")
public class PhotoController implements PhotoApi {

    private final PhotoService photoService;

    @Override
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.debug(file);
        log.info("Upload file with real file name: {}", file.getOriginalFilename());
        return ResponseEntity.ok(photoService.uploadFile(file));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> get(@PathVariable("id") String id) {
        log.info("Get photo by id: {}", id);
        return photoService.getPhoto(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<PhotoResponse> remove(@PathVariable("id") String id) {
        log.info("Remove photo by id: {}", id);
        return ResponseEntity.ok(photoService.remove(id));
    }
}
