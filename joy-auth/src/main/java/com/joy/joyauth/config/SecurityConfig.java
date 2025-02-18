package com.joy.joyauth.config;


import com.joy.joyauth.application.provider.MemberAuthenticationProvider;
import com.joy.joyauth.application.provider.MemberPrincipal;
import com.joy.joyauth.application.provider.SellerAuthenticationProvider;
import com.joy.joyauth.application.provider.SellerPrincipal;
import com.joy.joyauth.application.service.MemberService;
import com.joy.joyauth.application.service.SellerService;
import com.joy.joyauth.util.ParameterRequestMatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    @Order(1)
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, @Qualifier("sellerAuthenticationProvider") AuthenticationProvider sellerAuthenticationProvider) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(oidc -> oidc.clientRegistrationEndpoint(Customizer.withDefaults()));

        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoint = new LinkedHashMap<>();
        entryPoint.put(new ParameterRequestMatcher("client_id", "joy-ad"), new RedirectLoginUrlAuthenticationEntryPoint("/seller/login"));
        entryPoint.put(new ParameterRequestMatcher("client_id", "joy-payment"), new RedirectLoginUrlAuthenticationEntryPoint("/member/login"));



        return http
                .exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                        new DelegatingAuthenticationEntryPoint(entryPoint),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)

                )).authenticationProvider(sellerAuthenticationProvider)
                .build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain sellerSecurityMatcher(HttpSecurity http, AuthenticationProvider sellerAuthenticationProvider, AuthenticationProvider memberAuthenticationProvider) throws Exception {
        http.securityMatcher("/seller/**")
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/seller/login").permitAll()
                )
                .formLogin(it -> it
                        .loginPage("/seller/login")
                        .loginProcessingUrl("/seller/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(successHandler())
                )

                .authenticationProvider(sellerAuthenticationProvider);
        return http.build();
    }

    @Bean
    @Order(3)
    SecurityFilterChain memberSecurityMatcher(HttpSecurity http, AuthenticationProvider memberAuthenticationProvider) throws Exception {
        http.securityMatcher("/member/**")
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/member/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(it -> it
                        .loginPage("/member/login")
                        .loginProcessingUrl("/member/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(successHandler())
                )
                .authenticationProvider(memberAuthenticationProvider);
        return http.build();
    }

    private SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        savedRequestAwareAuthenticationSuccessHandler.setTargetUrlParameter("redirect_uri");

        return savedRequestAwareAuthenticationSuccessHandler;
    }

    @Bean
    @Order
    SecurityFilterChain defaultSecurityMatcher(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .anyRequest().hasRole("ADMIN")
        );
        return http.build();
    }


    @Bean("sellerAuthenticationProvider")
    AuthenticationProvider sellerAuthenticationProvider(SellerService sellerService) {
        return new SellerAuthenticationProvider(sellerService);
    }

    @Bean("memberAuthenticationProvider")
    AuthenticationProvider memberAuthenticationProvider(MemberService memberService) {
        return new MemberAuthenticationProvider(memberService);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                Authentication authenticationToken = context.getPrincipal();

                // 사용자 정보를 가져오기 (예: CustomUserDetails 사용)
                Object principal = authenticationToken.getPrincipal();
                if (principal instanceof SellerPrincipal) {
                    context.getClaims().claim("id", ((SellerPrincipal) principal).getId());
                } else if (principal instanceof MemberPrincipal) {
                    context.getClaims().claim("id", ((MemberPrincipal) principal).getId());
                }
            }
        };
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest
                        .toStaticResources()
                        .atCommonLocations()
                );
    }
}
