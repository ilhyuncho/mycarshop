package com.carshop.mycarshop.dto.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberSecurityDTO extends User {

    private String memberId;
    private String memberPw;
    private String email;
    private boolean isDel;
    private boolean isSocial;
    private boolean verified;       // 이메일 인증 유무


    public MemberSecurityDTO(String username, String password, String email, boolean isDel,
                             boolean isSocial, boolean enabled,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);

        this.memberId = username;
        this.memberPw = password;
        this.email = email;
        this.isDel = isDel;
        this.isSocial = isSocial;
        this.verified = enabled;
    }
}