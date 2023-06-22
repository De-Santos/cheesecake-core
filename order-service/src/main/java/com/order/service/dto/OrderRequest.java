package com.order.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Jacksonized
public class OrderRequest {

    @JsonProperty("user_id")
    private final Long userId;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("products")
    private final List<OrderProductRequest> products;

    @JsonProperty("required_done_time")
    private final Date requiredDoneTime;
}
