package com.sms.service.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class NotificationPrincipal {
    private final Long userId;
    private final String email;
    private final String phone;
    private final String username;
}