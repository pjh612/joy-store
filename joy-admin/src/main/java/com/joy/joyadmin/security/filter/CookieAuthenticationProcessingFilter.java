package com.joy.joyadmin.security.filter;

import com.joy.joyadmin.security.UserDetailsExtractor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Arrays;

public class CookieAuthenticationProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    private final UserDetailsExtractor<?> sellerExtractor;
    private final String[] permitAllPaths;
    private final PathMatcher pathMatcher;

    public CookieAuthenticationProcessingFilter(UserDetailsExtractor<?> sellerExtractor, String[] permitAllPaths) {
        this.sellerExtractor = sellerExtractor;
        this.permitAllPaths = permitAllPaths;

        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        if (isPermitAllPath(request.getRequestURI())) {
            return null;
        }
        return sellerExtractor.extract(request);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return sellerExtractor.extractCredential(request);
    }

    private boolean isPermitAllPath(String requestUri) {
        return Arrays.stream(permitAllPaths).anyMatch(path -> pathMatcher.match(path, requestUri));
    }
}
