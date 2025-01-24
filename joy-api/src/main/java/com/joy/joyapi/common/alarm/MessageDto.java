package com.joy.joyapi.common.alarm;

public record MessageDto(String targetId, String type, String body) {
    private static final String DEFAULT_MESSAGE_TYPE = "message";

    public static MessageDto of(String targetId, String message) {
        return new MessageDto(targetId, DEFAULT_MESSAGE_TYPE, message);
    }

    public static MessageDto of(String targetId, String type, String message) {
        return new MessageDto(targetId, type, message);
    }
}
