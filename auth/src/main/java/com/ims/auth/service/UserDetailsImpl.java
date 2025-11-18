package com.ims.auth.service;

import com.ims.common.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String role;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long userId, String username, String firstname, String lastname, String password, String role, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getRole(),
                List.of(authority)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
