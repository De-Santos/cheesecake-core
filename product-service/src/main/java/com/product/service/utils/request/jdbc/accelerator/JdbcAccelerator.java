package com.product.service.utils.request.jdbc.accelerator;

import com.product.service.utils.protector.Protector;
import com.product.service.utils.request.jdbc.accelerator.query.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class JdbcAccelerator {
    private final JdbcTemplate jdbc;
    private final Environment environment;

    private static final String VERSION_ID_IS_NULL = "versionId is null";
    private static final String ID_IS_NULL = "id is null";
    private static final String NAME_IS_NULL = "name is null";

    // TODO: 5/14/2023 create method saveBannerPhoto
    // TODO: 5/14/2023 create method saveDescriptionPhoto
    // TODO: 5/14/2023 create method getBannerPhoto
    // TODO: 5/14/2023 create method getDescriptionPhoto

    private void logger(String query) {
        if (Boolean.TRUE.equals(environment.getProperty("accelerator.show-sql", boolean.class, false)))
            log.info(query);
    }


    //PRODUCT
    public boolean existProductByVersionId(UUID versionId) {
        Protector.nonNull(new IllegalArgumentException(VERSION_ID_IS_NULL), versionId);
        logger(ProductQuery.EXIST_BY_VERSION_ID.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(ProductQuery.EXIST_BY_VERSION_ID.query, Boolean.class, versionId));
    }

    public boolean existProductByName(String name) {
        Protector.nonNull(new IllegalArgumentException(NAME_IS_NULL), name);
        logger(ProductQuery.EXIST_BY_NAME.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(ProductQuery.EXIST_BY_NAME.query, Boolean.class, name));
    }

    public boolean existProductByVersionIdAndActiveIsTrue(UUID versionId) {
        Protector.nonNull(new IllegalArgumentException(VERSION_ID_IS_NULL), versionId);
        logger(ProductQuery.EXIST_BY_VERSION_ID.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(ProductQuery.EXIST_BY_VERSION_ID.query, Boolean.class, versionId, true));
    }

    public UUID getProductVersionIdById(Long id) {
        Protector.nonNull(new IllegalArgumentException(ID_IS_NULL), id);
        logger(ProductQuery.SELECT_VERSION_ID_BY_ID.query);
        return jdbc.queryForObject(ProductQuery.SELECT_VERSION_ID_BY_ID.query, UUID.class, id);
    }

    //DRAFT PRODUCT
    public boolean existDraftById(Long id) {
        Protector.nonNull(new IllegalArgumentException(ID_IS_NULL), id);
        logger(DraftQuery.EXIST_BY_ID.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(DraftQuery.EXIST_BY_ID.query, Boolean.class, id));
    }

    public boolean existDraftByParentVersionId(UUID versionId) {
        Protector.nonNull(new IllegalArgumentException(VERSION_ID_IS_NULL), versionId);
        logger(DraftQuery.EXIST_BY_PARENT_VERSION_ID.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(DraftQuery.EXIST_BY_PARENT_VERSION_ID.query, Boolean.class, versionId));
    }

    public List<Long> getAllDraftIds() {
        return jdbc.queryForList(DraftQuery.SELECT_IDS.query, Long.class);
    }

    //ARCHIVE PRODUCT
    public boolean existArchiveByVersionId(UUID versionId) {
        Protector.nonNull(new IllegalArgumentException(VERSION_ID_IS_NULL), versionId);
        logger(ArchiveProductQuery.EXIST_BY_VERSION_ID.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(ArchiveProductQuery.EXIST_BY_VERSION_ID.query, Boolean.class, versionId));
    }

    //DESCRIPTION PHOTO
    @Transactional
    public void duplicateDescriptionPhotoById(Long id, Long fileCollectionId) {
        Protector.notNullRequired(new IllegalArgumentException(ID_IS_NULL), id, fileCollectionId);
        logger(DescriptionPhotoQuery.DUPLICATE_BY_ID.query);
        jdbc.update(DescriptionPhotoQuery.DUPLICATE_BY_ID.query, fileCollectionId, id);
    }

    //BANNER PHOTO
    @Transactional
    public void duplicateBannerPhotoById(Long id, Long fileCollectionId) {
        Protector.notNullRequired(new IllegalArgumentException(ID_IS_NULL), id, fileCollectionId);
        logger(BannerPhotoQuery.DUPLICATE_BY_ID.query);
        jdbc.update(BannerPhotoQuery.DUPLICATE_BY_ID.query, fileCollectionId, id);
    }
}
