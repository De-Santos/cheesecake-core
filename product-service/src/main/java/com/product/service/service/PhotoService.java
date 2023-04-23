package com.product.service.service;


import com.product.service.dto.photo.PhotoResponse;
import com.product.service.utils.additional.ProductChecker;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.PhotoRequestConstructor;
import com.product.service.utils.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRequestConstructor photoRequestConstructor;
    private final ProductChecker productChecker;
    private final Validator validator;
    private final Convertor convertor;

    // FIXME: 4/22/2023
    public ResponseEntity<byte[]> getPhoto(String draftId, UUID id) {
//        log.info("Get photo by id: {}", id);
//        productChecker.checkDraft(draftId);
//        return convertor.mergeToPhotoResponse(photoRequestConstructor.getPhoto(draftId, id));
        return null;
    }

    // FIXME: 4/22/2023
    public String upload(@NonNull MultipartFile file, String draftId) {
//        log.info("Upload file with name: {}", file.getOriginalFilename());
//        validator.validateSuppliedFile(file);
//        productChecker.checkDraft(draftId);
//        return photoRequestConstructor.uploadFile(file, draftId);
        return null;
    }

    // FIXME: 4/22/2023
    public String uploadDescription(@NonNull MultipartFile file, String draftId) {
//        log.info("Upload file with name: {}", file.getOriginalFilename());
//        validator.validateSuppliedFile(file);
//        productChecker.checkDraft(draftId);
//        return photoRequestConstructor.uploadDescriptionFile(file, draftId);
        return null;
    }

    // FIXME: 4/22/2023
    public String insert(@NonNull MultipartFile file, String draftId, Integer position) {
//        log.info("Upload file with position: {} for draft product by id: {}", position, draftId);
//        validator.validateSuppliedFile(file);
//        productChecker.checkDraft(draftId);
//        return photoRequestConstructor.uploadFile(file, draftId, position);
        return null;
    }

    public PhotoResponse remove(String draftId, UUID id) {
        log.info("Remove file by id: {}", id);
        return photoRequestConstructor.removeFile(draftId, id);
    }
}
