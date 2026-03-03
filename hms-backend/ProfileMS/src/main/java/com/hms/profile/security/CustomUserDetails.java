package com.hms.profile.security;


import com.hms.profile.enums.Roles;
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
