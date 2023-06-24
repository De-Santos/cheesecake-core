package com.order.service.utils.request.accelerator;

import com.order.service.entities.additional.ProcessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JdbcAccelerator {
    private final JdbcTemplate jdbc;

    private static final String SELECT_PRODUCT_PRICE_BY_VERSION_ID = "SELECT COALESCE(sale_price, price) AS final_price FROM products WHERE version_id = ?";
    private static final String UPDATE_PRODUCT_STATUS_BY_ID = "UPDATE orders SET status = ? WHERE id = ?";


    public BigDecimal getProductPriceByVersionId(UUID productVersionId) {
        return jdbc.queryForObject(SELECT_PRODUCT_PRICE_BY_VERSION_ID, BigDecimal.class, productVersionId);
    }

    public void disableOrder(Long orderId) {
        this.updateStatus(orderId, ProcessStatus.REJECTED_BY_USER);
    }

    public void updateStatus(Long orderId, ProcessStatus processStatus) {
        jdbc.update(UPDATE_PRODUCT_STATUS_BY_ID, processStatus.name(), orderId);
    }

}
