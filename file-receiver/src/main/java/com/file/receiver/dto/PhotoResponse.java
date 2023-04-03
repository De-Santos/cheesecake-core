package com.file.receiver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoResponse {
    private final String id;
    private final String realPhotoName;
    private final byte[] photo;
}
