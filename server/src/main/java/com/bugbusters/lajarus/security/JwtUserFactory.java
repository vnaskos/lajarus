package com.bugbusters.lajarus.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.bugbusters.lajarus.security.entity.Authority;
import com.bugbusters.lajarus.security.entity.User;
import java.util.ArrayList;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName().name()));
        }
        
        return grantedAuthorities;
    }
}
