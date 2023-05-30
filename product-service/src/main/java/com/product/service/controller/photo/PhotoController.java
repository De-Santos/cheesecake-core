package com.product.service.controller.photo;


import com.product.service.dto.photo.PhotoResponse;
import com.product.service.service.PhotoService;
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
    public ResponseEntity<Long> uploadBannerFile(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("draftId") Long draftId) {
        log.info("Upload file with real file name: {}", file.getOriginalFilename());
        return ResponseEntity.ok(photoService.upload(file, draftId));
    }

    @Override
    @PostMapping("/upload/description")
    public ResponseEntity<Long> uploadDescriptionFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("draftId") Long draftId) {
        log.info("Upload description file with real file name: {}", file.getOriginalFilename());
        return ResponseEntity.ok(photoService.uploadDescription(file, draftId));
    }

    @Override
    @PostMapping("/upload/insert")
    public ResponseEntity<Long> insertUploadFile(@RequestParam("file") MultipartFile fileRequest,
                                                 @RequestParam("draftId") Long draftId,
                                                 @RequestParam("position") Integer position) {
        log.info("Upload fil with real file name: {}, in position: {}, for draft product: {}",
                fileRequest.getOriginalFilename(), position, draftId);
        return ResponseEntity.ok(photoService.insert(fileRequest, draftId, position));
    }

    @Override
    @GetMapping("/banner/{id}")
    public ResponseEntity<byte[]> getBannerPhoto(@PathVariable("id") Long id) {
        log.info("Get banner photo by id: {}", id);
        return photoService.getBannerPhoto(id);
    }

    @Override
    @GetMapping("/description/{id}")
    public ResponseEntity<byte[]> getDescriptionPhoto(@PathVariable("id") Long id) {
        log.info("Get description photo by id: {}", id);
        return photoService.getDescriptionPhoto(id);
    }

    @Override
    @DeleteMapping("/banner")
    public ResponseEntity<PhotoResponse> removeBannerPhoto(@RequestParam("id") Long id,
                                                           @RequestParam("draftId") Long draftId) {
        log.info("Remove banner photo by id: {}", id);
        return ResponseEntity.ok(photoService.removeBannerPhoto(draftId, id));
    }

    @Override
    @DeleteMapping("/description")
    public ResponseEntity<PhotoResponse> removeDescriptionPhoto(@RequestParam("id") Long id,
                                                                @RequestParam("draftId") Long draftId) {
        log.info("Remove description photo by id: {}", id);
        return ResponseEntity.ok(photoService.removeDescriptionPhoto(draftId, id));
    }
}
