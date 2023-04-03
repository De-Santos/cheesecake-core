package com.file.receiver.utils.request;

import com.file.receiver.dao.PhotoRepository;
import com.file.receiver.dto.PhotoResponse;
import com.file.receiver.exceptions.exceptioins.found.FileNotFoundException;
import com.file.receiver.model.Photo;
import com.file.receiver.utils.ckecker.Checker;
import com.file.receiver.utils.convertor.Convertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Component
@RequiredArgsConstructor
public class PhotoRequestConstructor {
    private static final String FILE_NOT_FOUND_MESSAGE = "File not found by id: ";
    private final PhotoRepository photoRepository;
    private final Convertor convertor;
    private final Checker checker;

    public Photo getPhoto(String id) {
        log.info("Get photo by id: {}", id);
        return photoRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException(FILE_NOT_FOUND_MESSAGE + id));
    }

    public String uploadFile(MultipartFile file) {
        log.info("Upload file in database with name: {}", file.getOriginalFilename());
        return photoRepository.save(convertor.photoBuilder(file)).getId();
    }

    public PhotoResponse removeFile(String id) {
        log.info("Remove file by id: {}", id);
        checker.checkFileRelations(id);
        Photo photo = photoRepository.findById(id).orElseThrow(
                () -> new FileNotFoundException(FILE_NOT_FOUND_MESSAGE + id)
        );
        photoRepository.delete(photo);
        return convertor.convert(photo);
    }

    public void setInUse(String id) {
        log.info("Set state in use from database for file by id: {}", id);
        Photo photo = photoRepository.findById(id).orElseThrow(
                () -> new FileNotFoundException(FILE_NOT_FOUND_MESSAGE + id)
        );
        photo.setInUse(Boolean.TRUE);
        photoRepository.save(photo);
    }
}
