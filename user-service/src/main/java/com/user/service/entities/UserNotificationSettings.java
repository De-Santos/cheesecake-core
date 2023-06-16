package com.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "user_notification_settings")
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationSettings {
    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean emailNotification;
    private Boolean smsNotification;
    private Boolean adsNotification;
}
