package com.joy.joyui.security;

import lombok.Getter;

@Getter
public enum CommonCookie {
    SESSION_COOKIE("smsk");

    private final String name;

    CommonCookie(String name) {
        this.name = name;
    }
}
