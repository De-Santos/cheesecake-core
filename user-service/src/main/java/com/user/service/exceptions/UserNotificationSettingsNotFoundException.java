package com.user.service.exceptions;

@SuppressWarnings("unused")
public class UserNotificationSettingsNotFoundException extends UserNotificationException {
    public static UserNotificationSettingsNotFoundException create(Long userId) {
        return new UserNotificationSettingsNotFoundException("User notification settings not found by user id: " + userId);
    }

    public UserNotificationSettingsNotFoundException() {
    }

    public UserNotificationSettingsNotFoundException(String message) {
        super(message);
    }

    public UserNotificationSettingsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotificationSettingsNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotificationSettingsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
