package com.user.service.utils.request.jdbc.accelerator;

import com.user.service.dto.basket.BasketProductResponse;
import com.user.service.utils.request.jdbc.accelerator.mapper.BasketProductResponseRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JdbcAccelerator {
    private final JdbcTemplate jdbc;

    private static final BasketProductResponseRowMapper basketProductResponseRowMapper = new BasketProductResponseRowMapper();

    private static final String SELECT_EXIST_BASKET_PRODUCT_BY_ID_AND_BASKET_ID = "SELECT EXISTS(SELECT 1 FROM basket_products WHERE product_version_id = ? AND basket_id = ?)";
    private static final String SELECT_BASKET_PRODUCT_ID = "SELECT id FROM  basket_products WHERE product_version_id = ? AND basket_id = ?";
    private static final String UPDATE_BASKET_PRODUCT_BY_BASKET_ID_AND_VERSION_ID = "UPDATE basket_products SET count = ? WHERE product_version_id = ? AND basket_id = ? RETURNING *";
    private static final String SELECT_EXIST_WISH_PRODUCT_BY_WISH_LIST_ID_AND_VERSION_ID = "SELECT EXISTS(SELECT 1 FROM wish_products WHERE wish_list_id = ? AND product_version_id = ?)";

    public Boolean existBasketProductBy(UUID versionId, Long basketId) {
        return jdbc.queryForObject(SELECT_EXIST_BASKET_PRODUCT_BY_ID_AND_BASKET_ID, Boolean.class, versionId, basketId);
    }

    public Optional<Long> getBasketProductIdBy(UUID versionId, Long basketId) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(SELECT_BASKET_PRODUCT_ID);
            ps.setObject(1, versionId, Types.OTHER);
            ps.setLong(2, basketId);
            return ps;
        };
        List<Long> results = jdbc.query(psc, new SingleColumnRowMapper<>(Long.class));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<BasketProductResponse> updateCountInBasketProductBy(UUID versionId, Long basketId) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_BASKET_PRODUCT_BY_BASKET_ID_AND_VERSION_ID);
            ps.setObject(1, versionId, Types.OTHER);
            ps.setLong(2, basketId);
            return ps;
        };
        List<BasketProductResponse> results = jdbc.query(psc, basketProductResponseRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Boolean existWishProductBy(Long wishListId, UUID versionId) {
        return jdbc.queryForObject(SELECT_EXIST_WISH_PRODUCT_BY_WISH_LIST_ID_AND_VERSION_ID, Boolean.class, wishListId, versionId);
    }

}
