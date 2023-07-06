package com.product.service.utils.request.jdbc.query;

import lombok.AllArgsConstructor;

@SuppressWarnings("all")
@AllArgsConstructor
public enum ArchiveProductQuery {
    EXIST_BY_VERSION_ID("SELECT EXISTS(SELECT 1 FROM archive_products WHERE version_id = ?)"),
    INSERT("""
            INSERT INTO archive_products
                (version_id, actual_product_id, name, description, price, create_date)
            VALUES
                (?, ?, ?, ?, ?, ?)
            RETURNING version_id
            """);

    public final String query;
}
