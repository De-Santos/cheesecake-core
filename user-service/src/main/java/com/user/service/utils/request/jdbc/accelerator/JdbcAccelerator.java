package com.user.service.utils.request.jdbc.accelerator;

import com.user.service.dto.basket.BasketProductResponse;
import com.user.service.dto.user.UserNotificationSettingsRequest;
import com.user.service.dto.user.UserNotificationSettingsResponse;
import com.user.service.dto.user.UserResponse;
import com.user.service.utils.request.jdbc.accelerator.mapper.BasketProductResponseRowMapper;
import com.user.service.utils.request.jdbc.accelerator.mapper.UserNotificationSettigsResponseRowMapper;
import com.user.service.utils.request.jdbc.accelerator.mapper.UserResponseRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JdbcAccelerator {
    private final JdbcTemplate jdbc;

    private static final BasketProductResponseRowMapper basketProductResponseRowMapper = new BasketProductResponseRowMapper();
    private static final UserResponseRowMapper userResponseRowMapper = new UserResponseRowMapper();
    private static final UserNotificationSettigsResponseRowMapper userNotificationSettingsResponseRowMapper = new UserNotificationSettigsResponseRowMapper();

    private static final String SELECT_EXIST_BASKET_PRODUCT_BY_ID_AND_BASKET_ID = "SELECT EXISTS(SELECT 1 FROM basket_products WHERE product_version_id = ? AND basket_id = ?)";
    private static final String SELECT_BASKET_PRODUCT_ID = "SELECT id FROM  basket_products WHERE product_version_id = ? AND basket_id = ?";
    private static final String UPDATE_BASKET_PRODUCT_BY_BASKET_ID_AND_VERSION_ID = "UPDATE basket_products SET count = ? WHERE product_version_id = ? AND basket_id = ? RETURNING *";
    private static final String SELECT_EXIST_WISH_PRODUCT_BY_WISH_LIST_ID_AND_VERSION_ID = "SELECT EXISTS(SELECT 1 FROM wish_products WHERE wish_list_id = ? AND product_version_id = ?)";
    private static final String SELECT_USER_RESPONSE_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_FILED_DELETED_BY_ID = "UPDATE users SET deleted = ? WHERE id = ? RETURNING *";
    private static final String SELECT_NOTIFICATION_SETTINGS_BY_ID = "SELECT * FROM user_notification_settings WHERE user_id = ?";
    private static final String UPDATE_NOTIFICATION_SETTINGS_BY_ID = "UPDATE user_notification_settings SET email_notification = ?, sms_notification = ?, ads_notification = ? WHERE user_id = ? RETURNING *";


    public Boolean existBasketProductBy(UUID versionId, Long basketId) {
        return jdbc.queryForObject(SELECT_EXIST_BASKET_PRODUCT_BY_ID_AND_BASKET_ID, Boolean.class, versionId, basketId);
    }

    public Optional<Long> getBasketProductIdBy(UUID versionId, Long basketId) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(SELECT_BASKET_PRODUCT_ID);
            ps.setObject(1, versionId, Types.OTHER);
            ps.setLong(2, basketId);
            return ps;
        };
        List<Long> results = jdbc.query(psc, new SingleColumnRowMapper<>(Long.class));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<BasketProductResponse> updateCountInBasketProductBy(UUID versionId, Long basketId) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_BASKET_PRODUCT_BY_BASKET_ID_AND_VERSION_ID);
            ps.setObject(1, versionId, Types.OTHER);
            ps.setLong(2, basketId);
            return ps;
        };
        List<BasketProductResponse> results = jdbc.query(psc, basketProductResponseRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Boolean existWishProductBy(Long wishListId, UUID versionId) {
        return jdbc.queryForObject(SELECT_EXIST_WISH_PRODUCT_BY_WISH_LIST_ID_AND_VERSION_ID, Boolean.class, wishListId, versionId);
    }

    public Optional<UserResponse> getUserResponseById(Long id) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(SELECT_USER_RESPONSE_BY_ID);
            ps.setLong(1, id);
            return ps;
        };
        List<UserResponse> results = jdbc.query(psc, userResponseRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<UserResponse> deletedById(Long userId) {
        List<UserResponse> results = jdbc.query(
                this.createQueryUpdateFiledDeletedById(userId, true),
                userResponseRowMapper
        );
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<UserResponse> restoreById(Long userId) {
        List<UserResponse> results = jdbc.query(
                this.createQueryUpdateFiledDeletedById(userId, false),
                userResponseRowMapper
        );
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    private PreparedStatementCreator createQueryUpdateFiledDeletedById(Long id, boolean deleted) {
        return con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_FILED_DELETED_BY_ID);
            ps.setBoolean(1, deleted);
            ps.setLong(2, id);
            return ps;
        };
    }

    public Optional<UserNotificationSettingsResponse> getUserNotificationSettings(Long id) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(SELECT_NOTIFICATION_SETTINGS_BY_ID);
            ps.setLong(1, id);
            return ps;
        };
        List<UserNotificationSettingsResponse> results = jdbc.query(psc, userNotificationSettingsResponseRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<UserNotificationSettingsResponse> updateUserNotificationSettings(UserNotificationSettingsRequest notificationSettingsRequest) {
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE_NOTIFICATION_SETTINGS_BY_ID);
            ps.setBoolean(1, notificationSettingsRequest.getEmailNotification());
            ps.setBoolean(2, notificationSettingsRequest.getSmsNotification());
            ps.setBoolean(3, notificationSettingsRequest.getAdsNotification());
            ps.setLong(4, notificationSettingsRequest.getId());
            return ps;
        };
        List<UserNotificationSettingsResponse> results = jdbc.query(psc, userNotificationSettingsResponseRowMapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
}
