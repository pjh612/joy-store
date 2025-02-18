package com.joy.joyauth.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class RedirectLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    private static final Log logger = LogFactory.getLog(RedirectLoginUrlAuthenticationEntryPoint.class);

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public RedirectLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String redirectUri = request.getParameter("redirect_uri");

        return super.determineUrlToUseForThisRequest(request, response, exception) + "?redirect_uri=" + redirectUri;
    }
}
