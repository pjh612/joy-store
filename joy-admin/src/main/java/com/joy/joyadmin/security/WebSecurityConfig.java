package com.joy.joyadmin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joyadmin.auth.client.SellerAuthClient;
import com.joy.joyadmin.security.filter.CookieAuthenticationProcessingFilter;
import com.joy.joyadmin.security.filter.SignInProcessingFilter;
import com.joy.joyadmin.security.handler.*;
import com.joy.joyadmin.seller.client.SellerClient;
import com.joy.joycommon.crypto.AesCipher;
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
    public UserDetailsExtractor<StoreSellerDetails> storeMemberPrincipalExtractor(SellerClient sellerClient, AesCipher aesCipher) {
        return new StoreSellerPrincipalExtractor(sellerClient, aesCipher);
    }

    public AbstractPreAuthenticatedProcessingFilter cookieAuthenticationProcessingFilter(String[] PERMIT_ALL_PATHS, SellerClient sellerClient, AesCipher aesCipher, AuthenticationManager authenticationManager) {
        CookieAuthenticationProcessingFilter cookieAuthenticationProcessingFilter = new CookieAuthenticationProcessingFilter(storeMemberPrincipalExtractor(sellerClient, aesCipher), PERMIT_ALL_PATHS);
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
    public AuthenticationProvider storeMemberAuthenticationProvider(SellerAuthClient sellerAuthClient) {
        return new StoreSellerAuthenticationProvider(sellerAuthClient);
    }

    @Bean
    public AuthenticationProvider cookieAuthenticationProvider(StoreSellerDetailsService storeSellerDetailsService) {
        return new CookieAuthenticationProvider(storeSellerDetailsService);
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
        return new StoreSellerSignInSuccessHandler(objectMapper, aesCipher);
    }

    @Bean
    public AuthenticationFailureHandler storeMemberSignInFailureHandler(ObjectMapper objectMapper) {
        return new StoreSellerSignInFailureHandler(objectMapper);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SellerClient sellerClient,
                                                   ObjectMapper objectMapper, AesCipher aesCipher, AuthenticationManager authenticationManager) throws Exception {
        String[] PERMIT_ALL_PATHS = {"/sign-up", "/api/sellers", "/sign-in", "/api/sign-in", "/img/**", "/js/**", "/css/**", "/favicon.ico"};

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
                .addFilterBefore(cookieAuthenticationProcessingFilter(PERMIT_ALL_PATHS, sellerClient, aesCipher, authenticationManager), AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterBefore(signInProcessingFilter(objectMapper, aesCipher, authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(StoreSellerDetailsService storeSellerDetailsService, SellerAuthClient sellerAuthClient) {
        return new ProviderManager(List.of(storeMemberAuthenticationProvider(sellerAuthClient), cookieAuthenticationProvider(storeSellerDetailsService)));
    }

    @Bean
    public AesCipher aesCipher(@Value("${aes.key}") String key) {
        return new AesCipher(key);
    }
}
