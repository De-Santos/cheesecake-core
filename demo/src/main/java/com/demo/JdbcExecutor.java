package com.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Objects;

@Log4j2
@Component
@RequiredArgsConstructor
public class JdbcExecutor {
    private final JdbcTemplate jdbc;

    private static final String SELECT_USERS = """
                SELECT users.id, users.name, upd.email, upd.phone_number, uns.email_notification, uns.sms_notification
                FROM users
                JOIN user_private_data AS upd ON users.id = upd.user_id
                JOIN user_notification_settings AS uns ON users.id = uns.user_id
            """;

    public void createTest() {
        this.test();
    }

    private void test() {
        jdbc.query(select(), new SimpleRowExtractor());
    }


    private PreparedStatementCreator select() {
        return con -> {
            PreparedStatement ps = con.prepareStatement(SELECT_USERS);
            ps.setFetchSize(1);
            return ps;
        };
    }

    public void create(UserRegistrationRequest userRegistrationRequest) {
        Long userId = insertUser(userRegistrationRequest);
        insertUserPrivateData(userRegistrationRequest, userId);
        insertUserNotificationSettings(userId);
        insertWishList(userId);
        insertBasket(userId);
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

    private static class SimpleRowExtractor implements ResultSetExtractor<String> {


        @Override
        public String extractData(ResultSet rs) throws SQLException, DataAccessException {
            int i = 0;
            while (rs.next()) {
                i++;
            }
            log.info("ResultSet size is: {}", i);
            return null;
        }
    }
}
