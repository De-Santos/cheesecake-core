package com.order.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.order.service.entities.additional.ProcessStatus;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Jacksonized
public class UpdateOrderRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;

    @JsonProperty("real_total_price")
    private BigDecimal realTotalPrice;

    @JsonProperty("process_status")
    private ProcessStatus processStatus;

    @JsonProperty("required_done_time")
    private final Date requiredDoneTime;

    @JsonProperty("order_product_list")
    private List<OrderProductRequest> orderProductList;
}
