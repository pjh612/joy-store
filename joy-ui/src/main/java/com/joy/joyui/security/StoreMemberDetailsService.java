package com.joy.joyui.security;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.member.client.MemberClient;
import com.joy.joyui.member.dto.FindMemberResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StoreMemberDetailsService implements UserDetailsService {
    private final MemberClient memberClient;

    public StoreMemberDetailsService(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ApiResponse<FindMemberResponse> response = memberClient.findByUsername(username);
            FindMemberResponse member = response.getData();

            return new StoreMemberDetails(member.getSeq(), member.getId(), member.getPassword(), member.getName());
        } catch (Exception e) {
            throw new UsernameNotFoundException("아이디/비밀번호를 확인해주세요.");
        }
    }
}
