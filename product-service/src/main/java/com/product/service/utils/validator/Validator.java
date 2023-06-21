package com.product.service.utils.validator;


import com.product.service.exception.exceptions.file.photo.empty.EmptyFileException;
import com.product.service.exception.exceptions.file.photo.exceeded.FileSizeExceededException;
import com.product.service.exception.exceptions.file.photo.invalid.InvalidFileTypeException;
import com.product.service.exception.exceptions.file.photo.main.NullFileException;
import com.product.service.exception.exceptions.file.photo.nullable.NullFileContentTypeException;
import com.product.service.exception.exceptions.file.photo.nullable.NullFileNameException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {
    // TODO: 3/29/2023 make configuration from database
    private static final Long MAX_FILE_SIZE = 100L; // MB

    public static void validateObtainFile(MultipartFile file) {
        validateNullable(file);
        validateFileSize(file);
        validateFileType(file);
    }

    private static void validateFileSize(MultipartFile file) {
        long maxSize = MAX_FILE_SIZE * 1024 * 1024;
        if (file.getSize() > maxSize) throw new FileSizeExceededException("Maximal file size(MB) is: " + MAX_FILE_SIZE);
    }

    private static void validateFileType(MultipartFile file) {
        String fileType = file.getContentType();
        if (Objects.isNull(fileType) || (!fileType.equals("image/png") && !fileType.equals("image/jpeg"))) {
            throw new InvalidFileTypeException("Only PNG and JPEG file types are allowed.");
        }
    }

    private static void validateNullable(MultipartFile file) {
        if (Objects.isNull(file)) throw new NullFileException("File is null");
        if (file.isEmpty()) throw new EmptyFileException("File is empty");
        if (file.getName().isEmpty()) throw new NullFileNameException("File name is null or empty.");
        if (Objects.isNull(file.getOriginalFilename()))
            throw new NullFileNameException("File name is null or empty.");
        if (Objects.isNull(file.getContentType()))
            throw new NullFileContentTypeException("File type is null.");
    }
}
