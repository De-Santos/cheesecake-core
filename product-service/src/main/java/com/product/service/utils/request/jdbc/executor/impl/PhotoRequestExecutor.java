package com.product.service.utils.request.jdbc.executor.impl;

import com.product.service.entity.additional.Photo;
import com.product.service.utils.request.jdbc.executor.JdbcExecutor;
import com.product.service.utils.request.jdbc.executor.additional.mapper.PhotoRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Component
@AllArgsConstructor
public class PhotoRequestExecutor implements JdbcExecutor<Photo, Long> {
    private final JdbcTemplate template;

    private static final String IDS_MUST_BE_NOT_NULL_MESSAGE = "Ids must be not null";
    private static final String ID_MUST_BE_NOT_NULL_MESSAGE = "Id must be not null";
    private static final String ID_IN_OBTAINED_IDS_MUST_BE_NOT_NULL  = "Id in obtained ids must be not null";

    private static final String INSERT_ONE = "INSERT INTO photos (id, position, media_type, real_photo_name, image) VALUES (?, ?, ?, ?, ?) RETURNING id";
    private static final String SELECT_BY_ID = "SELECT * FROM photos WHERE id = ?";
    private static final String EXISTS_BY_ID = "SELECT EXISTS(SELECT 1 FROM photos WHERE id = ?)";
    private static final String SELECT_ALL = "SELECT * FROM photos";
    private static final String SELECT_ALL_BY_ID = "SELECT * FROM photos WHERE id IN (?)";
    private static final String SELECT_COUNT = "SELECT COUNT(*) FROM photos";
    private static final String DELETE_BY_ID = "DELETE FROM photos WHERE id = ?";
    private static final String DELETE_ALL_BY_ID = "DELETE FROM photos WHERE id IN (?)";
    private static final String DELETE_ALL = "DELETE FROM photos";

    @NonNull
    @Transactional
    @Override
    public <S extends Photo> S save(@NonNull S entity) {
        Assert.notNull(entity, "Entity must not be null");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ONE, new String[]{"id"});
            ps.setInt(1, entity.getPosition());
            ps.setString(2, entity.getMediaType());
            ps.setString(3, entity.getRealPhotoName());
            ps.setBytes(4, entity.getImage());
            return ps;
        }, keyHolder);
        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return entity;
    }

    @NonNull
    @Transactional
    @Override
    public <S extends Photo> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
        Assert.notNull(entities, "Entity must not be null");
        Collection<S> entityCollection = (Collection<S>) entities;
        Assert.noNullElements(entityCollection, "Collection cannot contain null elements");

        List<S> savedEntities = new ArrayList<>(entityCollection.size());
        int[] generatedKeys = template.batchUpdate(INSERT_ONE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(@NonNull PreparedStatement ps, int i) throws SQLException {
                        S entity = entityCollection.stream()
                                .skip(i)
                                .findFirst()
                                .orElse(null);
                        Objects.requireNonNull(entity);
                        ps.setInt(1, entity.getPosition());
                        ps.setString(2, entity.getMediaType());
                        ps.setString(3, entity.getRealPhotoName());
                        ps.setBytes(4, entity.getImage());
                    }

                    @Override
                    public int getBatchSize() {
                        return entityCollection.size();
                    }
                }
        );

        List<Integer> keysList = Arrays.stream(generatedKeys)
                .boxed()
                .toList();

        for (int i = 0; i < keysList.size(); i++) {
            Number id = keysList.get(i);
            S entity = entityCollection.stream()
                    .skip(i)
                    .findFirst()
                    .orElse(null);
            Objects.requireNonNull(entity);
            entity.setId(id.longValue());
            savedEntities.add(entity);
        }
        return savedEntities;
    }

    @NonNull
    @Override
    public Optional<Photo> findById(@NonNull Long id) {
        Assert.notNull(id, ID_MUST_BE_NOT_NULL_MESSAGE);
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @NonNull
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
                ps.setLong(1, id);
                return ps;
            }
        };
        List<Photo> result = template.query(psc, new PhotoRowMapper());
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public boolean existsById(@NonNull Long id) {
        Assert.notNull(id, ID_MUST_BE_NOT_NULL_MESSAGE);
        return Objects.requireNonNull(template.queryForObject(EXISTS_BY_ID, Boolean.class));
    }

    @NonNull
    @Override
    public Iterable<Photo> findAll() {
        return template.query(SELECT_ALL, new PhotoRowMapper());
    }

    @NonNull
    @Override
    public Iterable<Photo> findAllById(@NonNull Iterable<Long> ids) {
        Assert.notNull(ids, IDS_MUST_BE_NOT_NULL_MESSAGE);
        Assert.noNullElements(new Object[]{ids}, ID_IN_OBTAINED_IDS_MUST_BE_NOT_NULL);
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @NonNull
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SELECT_ALL_BY_ID);
                List<String> list = new ArrayList<>();
                ids.iterator().forEachRemaining(id -> list.add(id.toString()));
                ps.setString(1, String.join(", ", list));
                return ps;
            }
        };
        return template.query(psc, new PhotoRowMapper());
    }

    @Override
    public long count() {
        return Objects.requireNonNull(template.queryForObject(SELECT_COUNT, Long.class));
    }

    @Override
    public void deleteById(@NonNull Long id) {
        Assert.notNull(id, IDS_MUST_BE_NOT_NULL_MESSAGE);
        template.update(DELETE_BY_ID, id);
    }

    @Override
    public void delete(@NonNull Photo entity) {
        Assert.notNull(entity, "Entity must be not null");
        Assert.notNull(entity.getId(), "Entity id must be not null");
        template.update(DELETE_BY_ID, entity.getId());
    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends Long> ids) {
        Assert.notNull(ids, IDS_MUST_BE_NOT_NULL_MESSAGE);
        Assert.noNullElements(new Object[]{ids}, ID_IN_OBTAINED_IDS_MUST_BE_NOT_NULL);
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @NonNull
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(DELETE_ALL_BY_ID);
                List<String> list = new ArrayList<>();
                ids.iterator().forEachRemaining(id -> list.add(id.toString()));
                ps.setString(1, String.join(", ", list));
                return ps;
            }
        };
        template.update(psc);
    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends Photo> entities) {
        Assert.notNull(entities, "Entity must be not null");
        Assert.noNullElements(new Object[]{entities}, ID_IN_OBTAINED_IDS_MUST_BE_NOT_NULL);
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @NonNull
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(DELETE_ALL_BY_ID);
                List<String> list = new ArrayList<>();
                entities.iterator().forEachRemaining(id -> list.add(id.getId().toString()));
                ps.setString(1, String.join(", ", list));
                return ps;
            }
        };
        template.update(psc);
    }

    @Override
    public void deleteAll() {
        template.update(DELETE_ALL);
    }
}
