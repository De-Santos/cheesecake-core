package com.product.service.utils.request.jdbc.accelerator.query;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductQuery {
    EXIST_BY_NAME("SELECT EXISTS(SELECT 1 FROM products WHERE name = ?)"),

    EXIST_BY_VERSION_ID("SELECT EXISTS(SELECT 1 FROM products WHERE version_id = ?)"),

    EXIST_BY_VERSION_ID_AND_ACTIVE("SELECT EXISTS(SELECT 1 FROM products WHERE version_id = ? AND active = ?)"),

    SELECT_VERSION_ID_BY_ID("SELECT version_id FROM products WHERE id = ?"),

    SELECT_ALL_VERSION_ID("SELECT version_id FROM products");

    public final String query;
}
