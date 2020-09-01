package com.tywdi.backend.security;

import com.tywdi.backend.service.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.security
 * Date: 31.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private JwtTokenService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) {

        final String token = (String) authentication.getCredentials();
        final String username = jwtService.getUsernameFromToken(token);

        return jwtService.validateToken(token)
                .map(aBoolean -> new JwtAuthenticatedProfile(username))
                .orElseThrow(() -> {
                    log.error(String.format("Invalid JWT Token: %s", token));
                    return new JwtAuthenticationException("JWT Token validation failed");
                });
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
