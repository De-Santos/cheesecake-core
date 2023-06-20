package com.order.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class OrderProductResponse {

    @JsonProperty("product_version_id")
    private UUID productVersionId;

    @JsonProperty("creation_time")
    private Date creationTime;

    @JsonProperty("count")
    private Integer count;
}
