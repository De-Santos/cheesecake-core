package com.file.receiver.utils.convertor;

import com.file.receiver.dto.PhotoResponse;
import com.file.receiver.exceptions.exceptioins.invalid.InvalidFileException;
import com.file.receiver.model.Photo;
import org.bson.types.Binary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
public class Convertor {

    public PhotoResponse convert(Photo photo) {
        return this.createPhotoResponse(photo);
    }

    public ResponseEntity<byte[]> mergeToPhotoResponse(Photo photo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getMediaType()));
        return new ResponseEntity<>(photo.getImage().getData(), headers, HttpStatus.OK);
    }

    private PhotoResponse createPhotoResponse(Photo photo) {
        return PhotoResponse.builder()
                .id(photo.getId())
                .realPhotoName(photo.getRealPhotoName())
                .photo(photo.getImage().getData())
                .build();
    }

    public Photo photoBuilder(MultipartFile file) {
        return Photo.builder()
                .inUse(Boolean.FALSE)
                .mediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).toString())
                .realPhotoName(file.getOriginalFilename())
                .image(this.getBinaryFromFile(file))
                .build();
    }

    private Binary getBinaryFromFile(MultipartFile file) {
        try {
            return new Binary(file.getBytes());
        } catch (IOException e) {
            throw new InvalidFileException(e);
        }
    }

}
