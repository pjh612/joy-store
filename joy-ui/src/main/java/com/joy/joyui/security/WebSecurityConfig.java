package com.joy.joyui.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joycommon.crypto.AesCipher;
import com.joy.joyui.auth.client.MemberAuthClient;
import com.joy.joyui.member.client.MemberClient;
import com.joy.joyui.security.filter.CookieAuthenticationProcessingFilter;
import com.joy.joyui.security.filter.SignInProcessingFilter;
import com.joy.joyui.security.handler.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import java.util.List;

@Configuration
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsExtractor<StoreMemberDetails> storeMemberPrincipalExtractor(MemberClient memberClient, AesCipher aesCipher) {
        return new StoreUserDetailsPrincipalExtractor(memberClient, aesCipher);
    }

    public AbstractPreAuthenticatedProcessingFilter cookieAuthenticationProcessingFilter(String[] PERMIT_ALL_PATHS, MemberClient memberClient, AesCipher aesCipher, AuthenticationManager authenticationManager) {
        CookieAuthenticationProcessingFilter cookieAuthenticationProcessingFilter = new CookieAuthenticationProcessingFilter(storeMemberPrincipalExtractor(memberClient, aesCipher), PERMIT_ALL_PATHS);
        cookieAuthenticationProcessingFilter.setAuthenticationSuccessHandler(cookieRefreshHandler());
        cookieAuthenticationProcessingFilter.setAuthenticationFailureHandler(cookieAuthenticationFailureHandler());
        cookieAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);

        return cookieAuthenticationProcessingFilter;
    }

    @Bean
    public AuthenticationFailureHandler cookieAuthenticationFailureHandler() {
        return new CookieAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler cookieRefreshHandler() {
        return new CookieRefreshHandler();
    }

    @Bean
    public AuthenticationProvider storeMemberAuthenticationProvider(MemberAuthClient memberAuthClient) {
        return new StoreMemberAuthenticationProvider(memberAuthClient);
    }

    @Bean
    public AuthenticationProvider cookieAuthenticationProvider(StoreMemberDetailsService storeMemberDetailsService) {
        return new CookieAuthenticationProvider(storeMemberDetailsService);
    }

    @Bean
    public UsernamePasswordAuthenticationFilter signInProcessingFilter(ObjectMapper objectMapper, AesCipher aesCipher, AuthenticationManager authenticationManager) {
        SignInProcessingFilter signInProcessingFilter = new SignInProcessingFilter();
        signInProcessingFilter.setFilterProcessesUrl("/api/sign-in");
        signInProcessingFilter.setAuthenticationSuccessHandler(storeMemberSignInSuccessHandler(objectMapper, aesCipher));
        signInProcessingFilter.setAuthenticationFailureHandler(storeMemberSignInFailureHandler(objectMapper));
        signInProcessingFilter.setAuthenticationManager(authenticationManager);

        return signInProcessingFilter;
    }

    @Bean
    public AuthenticationSuccessHandler storeMemberSignInSuccessHandler(ObjectMapper objectMapper, AesCipher aesCipher) {
        return new StoreMemberSignInSuccessHandler(objectMapper, aesCipher);
    }

    @Bean
    public AuthenticationFailureHandler storeMemberSignInFailureHandler(ObjectMapper objectMapper) {
        return new StoreMemberSignInFailureHandler(objectMapper);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, MemberClient memberClient,
                                                   ObjectMapper objectMapper, AesCipher aesCipher, AuthenticationManager authenticationManager) throws Exception {
        String[] PERMIT_ALL_PATHS = {"/sign-up", "/api/sign-up", "/sign-in", "/api/sign-in", "/img/**", "/js/**", "/css/**", "/favicon.ico"};

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(it -> {
                            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = it.requestMatchers(PERMIT_ALL_PATHS);
                            authorizedUrl.permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .exceptionHandling(it -> it.accessDeniedHandler(new CookieAccessDeniedHandler()).authenticationEntryPoint(new CookieAuthenticationEntryPoint()))
                .addFilterBefore(cookieAuthenticationProcessingFilter(PERMIT_ALL_PATHS, memberClient, aesCipher, authenticationManager), AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterBefore(signInProcessingFilter(objectMapper, aesCipher, authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(StoreMemberDetailsService storeMemberDetailsService, MemberAuthClient memberAuthClient) {
        return new ProviderManager(List.of(storeMemberAuthenticationProvider(memberAuthClient), cookieAuthenticationProvider(storeMemberDetailsService)));
    }

    @Bean
    public AesCipher aesCipher(@Value("${aes.key}") String key) {
        return new AesCipher(key);
    }
}
