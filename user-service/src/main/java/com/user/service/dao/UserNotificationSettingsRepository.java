package com.user.service.dao;

import com.user.service.entities.UserNotificationSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationSettingsRepository extends JpaRepository<UserNotificationSettings, Long> {
}
