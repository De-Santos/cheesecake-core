package com.product.service.utils.request.jdbc.accelerator.query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ArchiveProductQuery {
    EXIST_BY_VERSION_ID("SELECT EXISTS(SELECT 1 FROM archive_products WHERE version_id = ?)");
    public final String query;
}
