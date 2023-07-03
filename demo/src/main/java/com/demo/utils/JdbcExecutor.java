package com.demo.utils;

import com.demo.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class JdbcExecutor {
    private final JdbcTemplate jdbc;

    private static final String INSERT_DEFAULT_USER = """
                WITH inserted_user AS (
                    INSERT INTO users (blocked, deleted, "name", second_name)
                    VALUES (false, false, 'user', 'second name')
                    RETURNING id
                ), inserted_user_private_data AS (
                    INSERT INTO user_private_data (address, email, password, phone_number, user_id)
                    SELECT 'address', 'email@gmail.com', 'password', '909090909090', id
                    FROM inserted_user
                ), inserted_user_notification_settings AS (
                    INSERT INTO user_notification_settings (ads_notification, email_notification, sms_notification, user_id)
                    SELECT true, true, true, id
                    FROM inserted_user
                ), inserted_wich_list AS (
                    INSERT INTO wich_list (user_id)
                    SELECT id
                    FROM inserted_user
                )
                INSERT INTO basket (user_id)
                SELECT id
                FROM inserted_user;
            """;

    private static final String SELECT_USERS = """
                SELECT users.id, users.name, upd.email, upd.phone_number, uns.email_notification, uns.sms_notification
                FROM users
                JOIN user_private_data AS upd ON users.id = upd.user_id
                JOIN user_notification_settings AS uns ON users.id = uns.user_id
                LIMIT ?
                OFFSET ?
            """;
    private static final int BATCH_SIZE = 1000;


    public void createTest() {
        test();
    }

    private void test() {
        int offset = 0;
        List<Long> batchResults;

        do {
            batchResults = fetchUsers(offset);
            processBatch(batchResults);
            offset += BATCH_SIZE;
        } while (!batchResults.isEmpty());
    }

    private List<Long> fetchUsers(int offset) {
        return jdbc.query(SELECT_USERS, userRowMapper, BATCH_SIZE, offset);
    }

    private void processBatch(List<Long> batchResults) {
        log.info(batchResults.size());
    }

    private final RowMapper<Long> userRowMapper = (rs, rowNum) -> rs.getLong("id");

    @SuppressWarnings("unused")
    public void create(UserRegistrationRequest userRegistrationRequest) {
        Long userId = insertUser(userRegistrationRequest);
        insertUserPrivateData(userRegistrationRequest, userId);
        insertUserNotificationSettings(userId);
        insertWishList(userId);
        insertBasket(userId);
    }

    @SneakyThrows
    public void monoCreate() {
        jdbc.update(INSERT_DEFAULT_USER);
    }

    public Long insertUser(UserRegistrationRequest userRegistrationRequest) {
        String sql = "INSERT INTO users (blocked, deleted, name, second_name) VALUES (?, ?, ?, ?) RETURNING id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setBoolean(1, false);
                    ps.setBoolean(2, false);
                    ps.setString(3, userRegistrationRequest.getName());
                    ps.setString(4, userRegistrationRequest.getSecondName());
                    return ps;
                },
                keyHolder
        );
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void insertUserPrivateData(UserRegistrationRequest userRegistrationRequest, Long userId) {
        String sql = "INSERT INTO user_private_data (address, creation_time, email, password, phone_number, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, userRegistrationRequest.getAddress(), new Date(), userRegistrationRequest.getEmail(),
                userRegistrationRequest.getPassword(), userRegistrationRequest.getPhoneNumber(), userId);
    }

    public void insertUserNotificationSettings(Long userId) {
        String sql = "INSERT INTO user_notification_settings (ads_notification, email_notification, sms_notification, user_id) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, true, true, true, userId);
    }

    public void insertWishList(Long userId) {
        String sql = "INSERT INTO wich_list (user_id) VALUES (?)";
        jdbc.update(sql, userId);
    }

    public void insertBasket(Long userId) {
        String sql = "INSERT INTO basket (user_id) VALUES (?)";
        jdbc.update(sql, userId);
    }
}
