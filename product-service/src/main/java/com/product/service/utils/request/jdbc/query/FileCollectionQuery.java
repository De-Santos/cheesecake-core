package com.product.service.utils.request.jdbc.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FileCollectionQuery {
    MATCH_TO_ARCHIVE_PRODUCT("""
            UPDATE file_collection
            SET archive_id = ?, draft_id = NULL, product_id = NULL
            WHERE id = ?
            """),

    MATCH_TO_PRODUCT("""
            UPDATE file_collection
            SET archive_id = NULL, draft_id = NULL, product_id = ?
            WHERE id = ?
            """);
    public final String query;
}
