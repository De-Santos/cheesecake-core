package com.product.service.utils.request.jdbc.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DraftQuery {
    EXIST_BY_ID("SELECT EXISTS(SELECT 1 FROM drafts WHERE id = ?)"),

    EXIST_BY_PARENT_VERSION_ID("SELECT EXISTS(SELECT 1 FROM drafts WHERE parent_version_id = ?)"),

    SELECT_IDS("SELECT id FROM drafts");

    public final String query;
}
