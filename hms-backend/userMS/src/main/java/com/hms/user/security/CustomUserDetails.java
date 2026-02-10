package com.hms.user.security;

import com.hms.user.enums.Roles;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Roles role;

    public Collection<? extends GrantedAuthority> authorities;
}
