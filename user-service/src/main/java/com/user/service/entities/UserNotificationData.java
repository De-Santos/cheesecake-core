package com.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "user_notification_data")
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationData {
    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean emailNotification;
    private Boolean smsNotification;
    private Boolean adsNotification;
}
