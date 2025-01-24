package com.joy.joyadmin.security.handler;

import com.joy.joyadmin.security.CommonCookie;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Arrays;

public class CookieRefreshHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(Arrays.stream(CommonCookie.values()).map(CommonCookie::getName)))
                .forEach(cookie -> {
                    Cookie newCookie = new Cookie(cookie.getName(), cookie.getValue());
                    newCookie.setDomain("localhost");
                    newCookie.setPath("/");
                    newCookie.setMaxAge(3600);
                    newCookie.setHttpOnly(true);
                    newCookie.setSecure(false);
                    response.addCookie(newCookie);
                });
    }
}
