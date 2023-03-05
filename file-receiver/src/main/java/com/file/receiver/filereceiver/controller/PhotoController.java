package com.file.receiver.filereceiver.controller;

import com.file.receiver.filereceiver.model.Photo;
import com.file.receiver.filereceiver.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file/photo")
public class PhotoController implements PhotoApi {

    private final PhotoService photoService;

    @Override
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        log.debug(file);
        log.info("upload file with name: {}", file.getName());
        return ResponseEntity.ok(photoService.uploadFile(file));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Photo>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(photoService.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Photo> get(@PathVariable String id) {
        log.info("get photo by id: {}", id);
        return ResponseEntity.ok(photoService.getPhoto(id));
    }

    @Override
    @GetMapping("/is/{id}")
    public ResponseEntity<Boolean> fileExist(@PathVariable String id) {
        log.info("Exist file by id: {}", id);
        return ResponseEntity.ok(photoService.checkFileExists(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        log.info("remove photo by id: {}", id);
        photoService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
