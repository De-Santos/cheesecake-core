package com.order.service.utils.request.extracter;

import com.order.service.exception.exceptions.basket.duplicate.DuplicateBasketProductException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BasketProductsResultSetExtract implements ResultSetExtractor<Map<UUID, Integer>> {

    @Override
    public Map<UUID, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<UUID, Integer> basketProducts = new HashMap<>();
        while (rs.next()) {
            UUID productVersionId = rs.getObject("product_version_id", UUID.class);
            if (basketProducts.containsKey(productVersionId))
                throw DuplicateBasketProductException.create(productVersionId);
            basketProducts.put(
                    productVersionId,
                    rs.getInt("count")
            );
        }
        return basketProducts;
    }
}
