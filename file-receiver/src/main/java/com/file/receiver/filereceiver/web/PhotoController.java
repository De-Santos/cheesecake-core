package com.file.receiver.filereceiver.web;


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
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(photoService.uploadFile(file));
    }

    @GetMapping
    public List<Photo> getAll() {
        log.info("getAll");
        return photoService.getAll();
    }

    @GetMapping("/{id}")
    public Photo get(@PathVariable String id) {
        return photoService.getPhoto(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable String id) {
        photoService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
