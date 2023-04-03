package com.file.receiver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@Jacksonized
public class FileRequest {
    private final MultipartFile file;
}
