package com.msc.ms.authentification.user.model;

import lombok.Builder;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
public class UserDetailDTO implements UserDetails {

    private String password;
    private String username;

    private Boolean isActive;

    private Boolean expiredPassword;
    private List<SimpleGrantedAuthority> authorities;


    public UserDetailDTO(String username, String password, boolean isActive, boolean expiredPassword, List<String> permissions) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.expiredPassword = expiredPassword;
        this.authorities = new ArrayList<>();
        permissions.forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expiredPassword;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
