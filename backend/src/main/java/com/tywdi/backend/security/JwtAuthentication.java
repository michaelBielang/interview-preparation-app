package com.tywdi.backend.security;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.security
 * Date: 31.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
@Data
public class JwtAuthentication implements Authentication {

    private final String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
