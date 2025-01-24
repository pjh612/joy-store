package com.joy.joyadmin.security;

import lombok.Getter;

@Getter
public enum CommonCookie {
    SESSION_COOKIE("smska");

    private final String name;

    CommonCookie(String name) {
        this.name = name;
    }
}
