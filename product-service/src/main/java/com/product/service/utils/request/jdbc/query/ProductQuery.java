package com.product.service.utils.request.jdbc.query;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductQuery {
    EXIST_BY_NAME("SELECT EXISTS(SELECT 1 FROM products WHERE name = ?)"),

    EXIST_BY_VERSION_ID("SELECT EXISTS(SELECT 1 FROM products WHERE version_id = ?)"),

    EXIST_BY_VERSION_ID_AND_ACTIVE("SELECT EXISTS(SELECT 1 FROM products WHERE version_id = ? AND active = ?)"),

    SELECT_VERSION_ID_BY_ID("SELECT version_id FROM products WHERE id = ?"),

    SELECT_ALL_VERSION_ID("SELECT version_id FROM products"),

    UPDATE_PRODUCT_BY_ID("""
            UPDATE products
            SET version_id = ?, name = ?, description = ?, price = ?, active = ?, create_date = ?
            WHERE id = ?
            RETURNING id
            """);

    public final String query;
}
