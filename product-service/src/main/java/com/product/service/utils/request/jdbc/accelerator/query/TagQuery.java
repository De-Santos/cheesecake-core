package com.product.service.utils.request.jdbc.accelerator.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TagQuery {
    INSERT("INSERT INTO tags (tag_name) VALUES (?) RETURNING id"),
    DELETE("DELETE FROM tags WHERE id = ? RETURNING id, tag_name"),
    SELECT_ALL("SELECT * FROM tags");

    public final String query;
}
