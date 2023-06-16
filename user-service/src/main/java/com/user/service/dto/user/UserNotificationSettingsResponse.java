package com.user.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserNotificationSettingsResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("email_notification")
    private Boolean emailNotification;

    @JsonProperty("sms_notification")
    private Boolean smsNotification;

    @JsonProperty("ads_notification")
    private Boolean adsNotification;
}
