package com.tywdi.backend.security;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.security
 * Date: 31.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private final String message;

    public JwtAuthenticationException(String msg) {
        super(msg);
        this.message = msg;
    }
}
