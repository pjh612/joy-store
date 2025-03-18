package com.joy.joyui.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joycommon.api.response.ApiExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static java.nio.charset.StandardCharsets.UTF_8;

public class StoreMemberSignInFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;

    public StoreMemberSignInFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());
        response.setStatus(SC_BAD_REQUEST);
        ApiExceptionResponse responseDto = new ApiExceptionResponse(exception.getMessage());
        objectMapper.writeValue(response.getWriter(), responseDto);
    }
}
