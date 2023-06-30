package com.product.service.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class TagResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tag_name")
    private String tagName;
}
