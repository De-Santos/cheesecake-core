package com.order.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class RejectOrderRequest {

    @JsonProperty("admin_id")
    private final Long adminId;

    @JsonProperty("message")
    private final String message;
}
