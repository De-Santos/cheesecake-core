package com.file.receiver.utils.ckecker;

import com.file.receiver.dao.PhotoRepository;
import com.file.receiver.dto.FileRequest;
import com.file.receiver.exceptions.exceptioins.exist.FileAlreadyExistException;
import com.file.receiver.exceptions.exceptioins.found.FileNotFoundException;
import com.file.receiver.exceptions.exceptioins.use.FileInUseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Component
@RequiredArgsConstructor
public class Checker {
    private final PhotoRepository photoRepository;

    public void checkExistence(FileRequest fileRequest) {
        this.checkFileExistence(fileRequest.getFile());
    }

    private void checkFileExistence(MultipartFile file) {
        if (photoRepository.existsByRealPhotoName(file.getOriginalFilename()))
            throw new FileAlreadyExistException("File already exist.");
    }

    public void checkFileRelations(String id) {
        this.checkFileIdExistence(id);
        this.checkFileIsUsed(id);
    }

    private void checkFileIdExistence(String id) {
        if (!photoRepository.existsById(id))
            throw new FileNotFoundException("File does not exist by id: " + id);
    }

    private void checkFileIsUsed(String id) {
        if (photoRepository.existsPhotoByIdAndInUseIsTrue(id))
            throw new FileInUseException("File is used in the product(s)");
    }
}
