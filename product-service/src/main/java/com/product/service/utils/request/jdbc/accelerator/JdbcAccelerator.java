package com.product.service.utils.request.jdbc.accelerator;

import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import com.product.service.entity.additional.BannerPhoto;
import com.product.service.entity.additional.DescriptionPhoto;
import com.product.service.utils.build.EntityBuilder;
import com.product.service.utils.protector.Protector;
import com.product.service.utils.request.jdbc.accelerator.mapper.BannerPhotoRowMapper;
import com.product.service.utils.request.jdbc.accelerator.mapper.DescriptionPhotoRowMapper;
import com.product.service.utils.request.jdbc.accelerator.mapper.TagResponseRowMapper;
import com.product.service.utils.request.jdbc.accelerator.query.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class JdbcAccelerator {
    private final JdbcTemplate jdbc;
    private final Environment environment;
    private final EntityBuilder entityBuilder;

    private static final BannerPhotoRowMapper bannerPhotoRowMapper = new BannerPhotoRowMapper();
    private static final DescriptionPhotoRowMapper descriptionPhotoRowMapper = new DescriptionPhotoRowMapper();
    private static final TagResponseRowMapper tagResponseRowMapper = new TagResponseRowMapper();

    private static final String VERSION_ID_IS_NULL = "versionId is null";
    private static final String ID_IS_NULL = "id is null";
    private static final String NAME_IS_NULL = "name is null";
    private static final String ENTITY_IS_NULL = "entity is null";

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

    public List<UUID> getAllVersionId() {
        logger(ProductQuery.SELECT_ALL_VERSION_ID.query);
        return jdbc.queryForList(ProductQuery.SELECT_ALL_VERSION_ID.query, UUID.class);
    }

    @SuppressWarnings("unused")
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

    @Transactional
    public DescriptionPhoto saveDescriptionPhoto(DescriptionPhoto photo) {
        Protector.notNullRequired(new IllegalArgumentException(ENTITY_IS_NULL), photo);
        logger(DescriptionPhotoQuery.UPLOAD.query);

        if (Objects.isNull(photo.getId())) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(DescriptionPhotoQuery.UPLOAD.query, Statement.RETURN_GENERATED_KEYS);
                ps.setBytes(1, photo.getImage());
                ps.setString(2, photo.getMediaType());
                ps.setString(3, photo.getRealPhotoName());
                ps.setLong(4, photo.getFileCollection().getId());
                return ps;
            }, keyHolder);
            photo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(DescriptionPhotoQuery.UPDATE.query);
                ps.setLong(1, photo.getFileCollection().getId());
                ps.setString(2, photo.getMediaType());
                ps.setString(3, photo.getRealPhotoName());
                ps.setBytes(4, photo.getImage());
                ps.setLong(5, photo.getId());
                return ps;
            });
        }
        return photo;
    }

    @Transactional
    public Optional<DescriptionPhoto> findDescriptionPhoto(Long id) {
        Protector.nonNull(new IllegalArgumentException(ID_IS_NULL), id);
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(DescriptionPhotoQuery.SELECT_BY_ID.query);
            ps.setLong(1, id);
            return ps;
        };

        List<DescriptionPhoto> result = jdbc.query(psc, descriptionPhotoRowMapper);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    //BANNER PHOTO
    @Transactional
    public void duplicateBannerPhotoById(Long id, Long fileCollectionId) {
        Protector.notNullRequired(new IllegalArgumentException(ID_IS_NULL), id, fileCollectionId);
        logger(BannerPhotoQuery.DUPLICATE_BY_ID.query);
        jdbc.update(BannerPhotoQuery.DUPLICATE_BY_ID.query, fileCollectionId, id);
    }

    @Transactional
    public BannerPhoto saveBannerPhoto(BannerPhoto photo) {
        Protector.notNullRequired(new IllegalArgumentException(ENTITY_IS_NULL), photo);
        logger(BannerPhotoQuery.UPLOAD.query);
        if (Objects.isNull(photo.getId())) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(BannerPhotoQuery.UPLOAD.query, Statement.RETURN_GENERATED_KEYS);
                ps.setBytes(1, photo.getImage());
                ps.setString(2, photo.getMediaType());
                ps.setInt(3, photo.getPosition());
                ps.setString(4, photo.getRealPhotoName());
                ps.setLong(5, photo.getFileCollection().getId());
                return ps;
            }, keyHolder);
            photo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(BannerPhotoQuery.UPDATE.query);
                ps.setLong(1, photo.getFileCollection().getId());
                ps.setInt(2, photo.getPosition());
                ps.setString(3, photo.getMediaType());
                ps.setString(4, photo.getRealPhotoName());
                ps.setBytes(5, photo.getImage());
                ps.setLong(6, photo.getId());
                return ps;
            });
        }
        return photo;
    }

    @Transactional
    public Optional<BannerPhoto> findBannerPhoto(Long id) {
        Protector.nonNull(new IllegalArgumentException(ID_IS_NULL), id);
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(BannerPhotoQuery.SELECT_BY_ID.query);
            ps.setLong(1, id);
            return ps;
        };

        List<BannerPhoto> result = jdbc.query(psc, bannerPhotoRowMapper);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    //TAG
    public TagResponse createTag(TagRequest tagRequest) {
        logger(TagQuery.INSERT.query);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(TagQuery.INSERT.query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tagRequest.getTagName());
            return ps;
        }, keyHolder);
        return entityBuilder.build(tagRequest, Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    private void deleteAllTagMatching(Long tagId) {
        logger(TagQuery.DELETE_ALL_MATCHING.query);
        jdbc.update(TagQuery.DELETE_ALL_MATCHING.query, tagId);
    }

    public Optional<TagResponse> deleteTag(Long tagId) {
        logger(TagQuery.DELETE.query);
        this.deleteAllTagMatching(tagId);
        List<TagResponse> result = jdbc.query(TagQuery.DELETE.query, tagResponseRowMapper, tagId, tagId);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    public List<TagResponse> getAllTags() {
        logger(TagQuery.SELECT_ALL.query);
        return jdbc.query(TagQuery.SELECT_ALL.query, tagResponseRowMapper);
    }

    public boolean isTagNameExist(String tagName) {
        Protector.nonNull(new IllegalArgumentException(NAME_IS_NULL), tagName);
        logger(TagQuery.EXIST_BY_NAME.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(TagQuery.EXIST_BY_NAME.query, Boolean.class, tagName));
    }

    public boolean isTagExistById(Long id) {
        Protector.nonNull(new IllegalArgumentException(ID_IS_NULL), id);
        logger(TagQuery.EXIST_BY_ID.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(TagQuery.EXIST_BY_ID.query, Boolean.class, id));
    }

    public List<TagResponse> getTagsByDraftId(Long draftId) {
        Protector.nonNull(new IllegalArgumentException(ID_IS_NULL), draftId);
        logger(TagQuery.SELECT_BY_DRAFT_ID.query);
        return jdbc.query(TagQuery.SELECT_BY_DRAFT_ID.query, tagResponseRowMapper, draftId);
    }

    public List<TagResponse> addTagToDraft(Long draftId, Long id) {
        Protector.notNullRequired(new IllegalArgumentException(ID_IS_NULL), draftId, id);
        logger(TagQuery.MATCH_TO_DRAFT.query);
        jdbc.update(TagQuery.MATCH_TO_DRAFT.query, id, draftId);
        return this.getTagsByDraftId(draftId);
    }

    public boolean isTagMatchingToDraftProductById(Long tagId, Long draftId) {
        logger(TagQuery.EXIST_MATCHING_TO_DRAFT.query);
        return Boolean.TRUE.equals(jdbc.queryForObject(TagQuery.EXIST_MATCHING_TO_DRAFT.query, Boolean.class, tagId, draftId));
    }

    @Modifying
    public List<TagResponse> deleteTagMatchingToDraft(Long tagId, Long draftId) {
        Protector.notNullRequired(new IllegalArgumentException(ID_IS_NULL), tagId, draftId);
        logger(TagQuery.DELETE_MATCHING_TO_DRAFT.query);
        jdbc.update(TagQuery.DELETE_MATCHING_TO_DRAFT.query, tagId, draftId);
        return this.getTagsByDraftId(draftId);
    }
}
