package com.product.service.service;


import com.product.service.dao.PhotoRepository;
import com.product.service.dto.photo.PhotoResponse;
import com.product.service.utils.convertor.Convertor;
import com.product.service.utils.request.PhotoRequestConstructor;
import com.product.service.utils.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final PhotoRequestConstructor photoRequestConstructor;
    private final Validator validator;
    private final Convertor convertor;

    public ResponseEntity<byte[]> getPhoto(String id) {
        log.info("Get photo by id: {}", id);
        return convertor.mergeToPhotoResponse(photoRequestConstructor.getPhoto(id));
    }

    public String uploadFile(MultipartFile file) {
        log.info("Upload file with name: {}", file.getOriginalFilename());
        validator.validateSuppliedFile(file);
        return photoRequestConstructor.uploadFile(file);
    }

    public PhotoResponse remove(String id) {
        log.info("Remove file by id: {}", id);
        return photoRequestConstructor.removeFile(id);
    }

    public boolean checkFileExists(String id) {
        log.info("Check file existence by id: {}", id);
        return photoRepository.existsById(id);
    }

    public void use(List<String> ids) {
        log.info("Set state in use for file by id: {}", ids);
        ids.forEach(photoRequestConstructor::setInUse);
    }
}
