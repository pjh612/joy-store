package com.joy.joyapi.common.exception;

import com.joy.joycommon.api.response.ApiExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({BadCredentialsException.class, AuthenticationException.class})
    public ResponseEntity<ApiExceptionResponse> onBadCredentialsException(Exception e) {
        log.debug(e.getMessage(), e);

        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new ApiExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> onEntityNotFoundException(EntityNotFoundException e) {
        log.debug(e.getMessage(), e);

        return ResponseEntity.badRequest().body(new ApiExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiExceptionResponse> onIllegalArgumentException(IllegalArgumentException e) {
        log.debug(e.getMessage(), e);

        return ResponseEntity.badRequest().body(new ApiExceptionResponse(e.getMessage()));
    }
}
