package com.tywdi.backend.security.service;

import com.tywdi.backend.model.web.JwtTokenResponse;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface AuthenticationServiceInterface {
    JwtTokenResponse generateJWTToken(String email, String password);
}
