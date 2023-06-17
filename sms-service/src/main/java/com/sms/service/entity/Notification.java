package com.sms.service.entity;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Jacksonized
public class Notification {
    private final Long id;
    private final UUID uuid;
    private final NotificationMethod method;
    private final NotificationPrincipal principal;
    private final String message;
    private final Date time;
}