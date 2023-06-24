package com.order.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class RejectOrderResponse {

    @JsonProperty("user_id")
    private final Long userId;

    @JsonProperty("order_id")
    private final Long orderId;

    @JsonProperty("message")
    private final String message;
}
