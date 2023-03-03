package com.file.receiver.filereceiver.utils;

import com.file.receiver.filereceiver.model.Photo;
import lombok.extern.log4j.Log4j2;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
public class PhotoUtil {

    public Binary getBinaryFromFile(MultipartFile file) {
        try {
            return new Binary(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File is invalid", e);
        }
    }

    public Photo photoBuilder(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Binary binary = this.getBinaryFromFile(file);
        Photo photo = Photo.builder()
                .title(fileName)
                .image(binary)
                .build();
        log.debug(photo);
        return photo;
    }

}
