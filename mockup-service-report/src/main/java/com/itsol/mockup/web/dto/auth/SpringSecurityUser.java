package com.itsol.mockup.web.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author anhvd_itsol
 */
@Data
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SpringSecurityUser implements UserDetails {
     String username;
     String password;
     String email;
     Date lastPasswordReset;
     Collection<? extends GrantedAuthority> authorities;
     Boolean accountNonExpired = true;
     Boolean accountNonLocked = true;
     Boolean credentialsNonExpired = true;
     Boolean enabled = true;

    public SpringSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
//        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }

    public SpringSecurityUser(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
