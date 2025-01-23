package com.joy.joycommon.api.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiExceptionResponse {
    private String message;
    private String code;

    public ApiExceptionResponse(String message) {
        this.message = message;
    }
}
