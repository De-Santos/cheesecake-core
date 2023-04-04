package com.product.service.utils.validator;

import com.product.service.exception.exceptions.photo.empty.EmptyFileException;
import com.product.service.exception.exceptions.photo.exceeded.FileSizeExceededException;
import com.product.service.exception.exceptions.photo.file.NullFileException;
import com.product.service.exception.exceptions.photo.invalid.InvalidFileTypeException;
import com.product.service.exception.exceptions.photo.nullable.NullFileContentTypeException;
import com.product.service.exception.exceptions.photo.nullable.NullFileNameException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Log4j2
@Component
@AllArgsConstructor
public class Validator {
    // TODO: 3/29/2023 make configuration from database
    private static final Long MAX_FILE_SIZE = 100L; // MB

    public void validateSuppliedFile(MultipartFile file) {
        this.validateNullable(file);
        this.validateFileSize(file);
        this.validateFileType(file);
    }

    private void validateFileSize(MultipartFile file) {
        long maxSize = MAX_FILE_SIZE * 1024 * 1024;
        if (file.getSize() > maxSize) throw new FileSizeExceededException("Maximal file size(MB) is: " + MAX_FILE_SIZE);
    }

    private void validateFileType(MultipartFile file) {
        String fileType = file.getContentType();
        // TODO: 4/3/2023 refactor me please
        if (Objects.isNull(fileType) || (!fileType.equals("image/png") && !fileType.equals("image/jpeg"))) {
            throw new InvalidFileTypeException("Only PNG and JPEG file types are allowed.");
        }
    }

    private void validateNullable(MultipartFile file) {
        if (Objects.isNull(file)) throw new NullFileException("File is null");
        if (file.isEmpty()) throw new EmptyFileException("File is empty");
        if (file.getName().isEmpty()) throw new NullFileNameException("File name is null or empty.");
        if (Objects.isNull(file.getOriginalFilename()))
            throw new NullFileNameException("File name is null or empty.");
        if (Objects.isNull(file.getContentType()))
            throw new NullFileContentTypeException("File type is null.");
    }
}
