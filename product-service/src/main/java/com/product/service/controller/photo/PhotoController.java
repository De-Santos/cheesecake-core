package com.product.service.controller.photo;


import com.product.service.dto.photo.PhotoResponse;
import com.product.service.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file/photo")
public class PhotoController implements PhotoApi {

    private final PhotoService photoService;

    @Override
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("draftId") String draftId) {
        log.debug(file);
        log.info("Upload file with real file name: {}", file.getOriginalFilename());
        return ResponseEntity.ok(photoService.upload(file, draftId));
    }

    @Override
    @PostMapping("/upload/insert")
    public ResponseEntity<String> insertUploadFile(@RequestParam("file") MultipartFile fileRequest,
                                                   @RequestParam("draftId") String draftId,
                                                   @RequestParam("position") Integer position) {
        log.info("Upload fil with real file name: {}, in position: {}, for draft product: {}",
                fileRequest.getOriginalFilename(), position, draftId);
        return ResponseEntity.ok(photoService.insert(fileRequest, draftId, position));
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<byte[]> get(@RequestParam("id") UUID id,
                                      @RequestParam("draftId") String draftId) {
        log.info("Get photo by id: {}", id);
        return photoService.getPhoto(draftId, id);
    }

    @Override
    @DeleteMapping("/")
    public ResponseEntity<PhotoResponse> remove(@RequestParam("id") UUID id,
                                                @RequestParam("draftId") String draftId) {
        log.info("Remove photo by id: {}", id);
        return ResponseEntity.ok(photoService.remove(draftId, id));
    }
}
