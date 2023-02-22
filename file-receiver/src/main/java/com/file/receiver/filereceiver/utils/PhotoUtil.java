package com.file.receiver.filereceiver.utils;

import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PhotoUtil {

    public Binary getBinaryFromFile(MultipartFile file) {
        try {
            return new Binary(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File is invalid", e);
        }
    }
}
