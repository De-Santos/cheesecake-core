package com.product.service.utils.request.jdbc.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TagQuery {
    INSERT("INSERT INTO tags (tag_name) VALUES (?) RETURNING id"),

    DELETE_ALL_MATCHING("DELETE FROM tag_collection_tags WHERE tag_id = ?"),

    DELETE("DELETE FROM tags WHERE id = ? RETURNING id, tag_name"),

    SELECT_ALL("SELECT * FROM tags"),

    EXIST_BY_NAME("SELECT EXISTS(SELECT 1 FROM tags WHERE tag_name = ?)"),

    EXIST_BY_ID("SELECT EXISTS(SELECT 1 FROM tags WHERE id = ?)"),

    EXIST_MATCHING_TO_DRAFT("""
            SELECT EXISTS(
                SELECT 1
                FROM tag_collection_tags tct
                JOIN tags t ON tct.tag_id = t.id
                JOIN tag_collection tc ON tct.tag_collection_id = tc.id
                JOIN drafts d ON tc.draft_product_hash = d.hash
                WHERE t.id = ? AND d.id = ?
            );
            """),

    MATCH_TO_DRAFT("""
            INSERT INTO tag_collection_tags VALUES (?, ?)
            """),

    SELECT_BY_DRAFT_ID("""
            SELECT t.id, t.tag_name
            FROM tags t
            JOIN tag_collection_tags tct ON t.id = tct.tag_id
            JOIN tag_collection tc ON tct.tag_collection_id = tc.id
            JOIN drafts d ON tc.draft_product_hash = d.hash
            WHERE d.id = ?
            """),

    DELETE_MATCHING_TO_DRAFT("""
            DELETE FROM tag_collection_tags tct
            USING tag_collection tc
            JOIN drafts d ON tc.draft_product_hash = d.hash
            WHERE tct.tag_id = ? AND d.id = ?
            """),

    MATCH_TO_ARCHIVE_PRODUCT("""
            UPDATE tag_collection
            SET archive_product_id = ?, draft_product_hash = NULL, product_id = NULL
            WHERE id = ?
            """),

    MATCH_TO_PRODUCT("""
            UPDATE tag_collection
            SET archive_product_id = NULL, draft_product_hash = NULL, product_id = ?
            WHERE id = ?
            """);

    public final String query;
}
