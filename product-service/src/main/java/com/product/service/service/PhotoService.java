package com.product.service.service;


import com.product.service.dto.photo.PhotoResponse;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.PhotoRequestConstructor;
import com.product.service.utils.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRequestConstructor photoRequestConstructor;
    private final ProductChecker productChecker;
    private final Convertor convertor;

    public ResponseEntity<byte[]> getBannerPhoto(Long id) {
        log.info("Get photo by id: {}", id);
        return convertor.mergeToPhotoResponse(photoRequestConstructor.getBannerPhoto(id));
    }

    public ResponseEntity<byte[]> getDescriptionPhoto(Long id) {
        log.info("Get photo by id: {}", id);
        return convertor.mergeToPhotoResponse(photoRequestConstructor.getDescriptionPhoto(id));
    }

    @Transactional
    public Long upload(@NonNull MultipartFile file, Long draftId) {
        log.info("Upload file with name: {}", file.getOriginalFilename());
        productChecker.checkDraft(draftId);
        return photoRequestConstructor.uploadFile(file, draftId);
    }

    @Transactional
    public Long uploadDescription(@NonNull MultipartFile file, Long draftId) {
        log.info("Upload file with name: {}", file.getOriginalFilename());
        productChecker.checkDraft(draftId);
        return photoRequestConstructor.uploadDescriptionFile(file, draftId);
    }

    @Transactional
    public Long insert(@NonNull MultipartFile file, Long draftId, Integer position) {
        log.info("Upload file with position: {} for draft product by id: {}", position, draftId);
        Validator.validateObtainFile(file);
        productChecker.checkDraft(draftId);
        return photoRequestConstructor.uploadFile(file, draftId, position);
    }

    public PhotoResponse removeBannerPhoto(Long draftId, Long id) {
        log.info("Remove banner photo by id: {}", id);
        productChecker.checkDraftById(draftId);
        BannerPhoto removedPhoto = photoRequestConstructor.removeBannerPhoto(id);
        photoRequestConstructor.fileCollectionNormalization(draftId);
        return convertor.convert(removedPhoto);
    }

    @Transactional
    public PhotoResponse removeDescriptionPhoto(Long draftId, Long id) {
        log.info("Remove description photo by id: {}", id);
        productChecker.checkDraftById(draftId);
        return convertor.convert(photoRequestConstructor.removeDescriptionPhoto(id));
    }

}
