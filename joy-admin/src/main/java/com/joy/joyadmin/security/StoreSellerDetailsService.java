package com.joy.joyadmin.security;

import com.joy.joyadmin.seller.client.SellerClient;
import com.joy.joyadmin.seller.dto.FindSellerResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StoreSellerDetailsService implements UserDetailsService {
    private final SellerClient sellerClient;

    public StoreSellerDetailsService(SellerClient sellerClient) {
        this.sellerClient = sellerClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ApiResponse<FindSellerResponse> response = sellerClient.findByUsername(username);
            FindSellerResponse seller = response.getData();

            return new StoreSellerDetails(seller.seq(), seller.username(), seller.password(), seller.name());
        } catch (Exception e) {
            throw new UsernameNotFoundException("아이디/비밀번호를 확인해주세요.");
        }
    }
}
