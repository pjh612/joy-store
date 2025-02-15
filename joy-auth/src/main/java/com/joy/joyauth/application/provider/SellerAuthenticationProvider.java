package com.joy.joyauth.application.provider;

import com.joy.joyauth.application.client.dto.SellerAuthResponse;
import com.joy.joyauth.application.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

@RequiredArgsConstructor
public class SellerAuthenticationProvider implements AuthenticationProvider {
    private final SellerService sellerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        SellerAuthResponse auth = sellerService.auth(username, password);

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        SellerPrincipal principal = new SellerPrincipal(
                auth.sellerId(),
                username,
                authorities
        );
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
