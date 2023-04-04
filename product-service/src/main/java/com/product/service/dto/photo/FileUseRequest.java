package com.product.service.dto.photo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class FileUseRequest {
    private final List<String> ids;
}
