package com.demo.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserNotificationSettings {
    private Long id;
    private Long userId;
    private Boolean emailNotification;
    private Boolean smsNotification;
    private Boolean adsNotification;
}
