package com.order.service.utils.request;

import com.order.service.utils.request.extracter.BasketProductsResultSetExtract;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidationAccelerator {

    private final JdbcTemplate jdbc;

    private static final BasketProductsResultSetExtract basketProductsResultSetExtract = new BasketProductsResultSetExtract();

    private static final String SELECT_EXIST_USER_BY_ID = "SELECT EXISTS(SELECT 1 FROM users WHERE id = ?)";
    private static final String SELECT_EXIST_PRODUCT_BY_VERSION_ID = "SELECT EXISTS(SELECT 1 FROM products WHERE version_id = ? AND active = true)";
    private static final String SELECT_EXIST_BASKET_BY_ID = "SELECT EXISTS(SELECT 1 FROM basket WHERE user_id = ?)";
    private static final String SELECT_BASKET_PRODUCTS_BY_BASKET_ID = "SELECT product_version_id, count FROM basket_products WHERE basket_id = ?";
    private static final String SELECT_EXIST_ORDER_BY_ID = "SELECT EXISTS(SELECT 1 FROM orders WHERE id = ?)";

    public Boolean existUserById(Long userId) {
        return jdbc.queryForObject(SELECT_EXIST_USER_BY_ID, Boolean.class, userId);
    }

    public Boolean existProductByVersionId(UUID productVersionId) {
        return jdbc.queryForObject(SELECT_EXIST_PRODUCT_BY_VERSION_ID, Boolean.class, productVersionId);
    }

    public Boolean existBasketById(Long basketId) {
        return jdbc.queryForObject(SELECT_EXIST_BASKET_BY_ID, Boolean.class, basketId);
    }

    public Map<UUID, Integer> getBasketProductsByBasketId(Long basketId) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(SELECT_BASKET_PRODUCTS_BY_BASKET_ID);
            ps.setLong(1, basketId);
            return ps;
        };
        return jdbc.query(psc, basketProductsResultSetExtract);
    }

    public Boolean existOrderById(Long id) {
        return jdbc.queryForObject(SELECT_EXIST_ORDER_BY_ID, Boolean.class, id);
    }
}
