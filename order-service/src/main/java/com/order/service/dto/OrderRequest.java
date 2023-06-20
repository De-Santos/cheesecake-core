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
public class OrderRequest {

    @JsonProperty("user_id")
    private final Long userId;

    @JsonProperty("count")
    private final Integer count;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("product_version_id")
    private final UUID productVersionId;

    @JsonProperty("required_done_time")
    private final Date requiredDoneTime;
}
