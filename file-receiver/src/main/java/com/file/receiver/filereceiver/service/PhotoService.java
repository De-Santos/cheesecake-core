package com.file.receiver.filereceiver.service;

import com.file.receiver.filereceiver.dao.PhotoRepository;
import com.file.receiver.filereceiver.model.Photo;
import com.file.receiver.filereceiver.utils.PhotoUtil;
import com.file.receiver.filereceiver.utils.exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoUtil photoUtil;

    public Photo getPhoto(String id) {
        return photoRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Photo with id " + id + " does not exist."));
    }

    public List<Photo> getAll() {
        return photoRepository.findAll();
    }

    public String uploadFile(MultipartFile file) {
        return photoRepository.save(photoUtil.photoBuilder(file)).getId();
    }

    public void remove(String id) {
        checkFileExists(id);
        photoRepository.deleteById(id);
    }

    private void checkFileExists(String id) {
        if (!photoRepository.existsById(id)) {
            throw new FileNotFoundException("Photo with id " + id + " does not exist.");
        }
    }
}
