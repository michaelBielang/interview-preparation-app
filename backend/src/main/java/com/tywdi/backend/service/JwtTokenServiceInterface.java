package com.tywdi.backend.service;

import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.service
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface JwtTokenServiceInterface {
    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);

    String generateToken(String email);

    Date localDateTimeToDate(LocalDateTime localDateTime);

    Boolean isTokenNotExpired(String token);

    Optional<Boolean> validateToken(String token);

    Date calculateExpirationDate();
}
