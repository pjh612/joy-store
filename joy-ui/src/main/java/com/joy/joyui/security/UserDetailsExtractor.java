package com.joy.joyui.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsExtractor<T extends UserDetails> {
    T extract(HttpServletRequest request);

    String extractCredential(HttpServletRequest request);
}
