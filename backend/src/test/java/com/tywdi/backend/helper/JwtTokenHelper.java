package com.tywdi.backend.helper;

import com.tywdi.backend.service.JwtTokenService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.helper
 * Date: 04.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

public class JwtTokenHelper {

    private static final JwtTokenService tokenService = new JwtTokenService("mySecret", 1);

    public static HttpHeaders withMail(final String email) {
        HttpHeaders headers = new HttpHeaders();
        String token = tokenService.generateToken(email);
        headers.setContentType(APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }

    public static HttpEntity<?> withMailHeader(final String email) {
        HttpHeaders headers = new HttpHeaders();
        String token = tokenService.generateToken(email);
        headers.setContentType(APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        return new HttpEntity<>("default", headers);
    }
}
